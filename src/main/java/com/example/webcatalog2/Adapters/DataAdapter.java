package com.example.webcatalog2.Adapters;

import com.example.webcatalog2.Model.Article;
import com.example.webcatalog2.db.DbHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataAdapter {
    private static DbHandler dbHandler;

    static {
        try {
            dbHandler = DbHandler.getInstance();
            dbHandler.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<Article>> getLines(String page, String title, String author, String key, String date) {
        ArrayList<Article> products = dbHandler.getAllArticles(title, author, key, date);
        if (page == null) page = "0";
        ArrayList<ArrayList<Article>> data = new ArrayList<>();
        for (int i = Integer.parseInt(page) * 3; i < Math.min(3 + Integer.parseInt(page) * 3, products.size() / 3 + 2) ; i++) {
            data.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {


                if (i*3 + j < products.size()){
                    System.out.println("data=" + data.size()+ ", products="+ products.size() +  "i=" + i*3 +", j="+ j);
                    data.get(i-Integer.parseInt(page) * 3).add(products.get(i*3 + j));
                }
            }
        }
        return data;
    }
    public static ArrayList<ArrayList<Article>> getLinesAll(String page, String title, String author, String key, String date) {
        ArrayList<Article> products = dbHandler.getAllArticles(title, author, key, date);
        System.out.println("all" + products.size()+ "pag" + page);
        if (page == null) page = "0";
        ArrayList<ArrayList<Article>> data = new ArrayList<>();
        for (int i = 0; i < products.size() ; i++) {
            data.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                if (i*3 + j < products.size())
                    data.get(i).add(products.get(i*3 + j));
            }
        }
        return data;
    }

    public static int getArticlesCount(){
        return 0;
    }

    public static int saveFile(String title, String author, String key, String date, String id){
        return dbHandler.addArticle(new Article(title, author, key, date, id));
    }

    public static int getLinesCount(String page, String title, String author, String key, String date) {
        return getLinesAll(page, title, author, key, date).size();
    }

    public static Article getArticle(String id) {
        return dbHandler.getArticle(id);
    }
}
