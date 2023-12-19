package com.example.webcatalog2.db;

import com.example.webcatalog2.Model.Article;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {

    private static final String CON_STR = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\articleDb.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    public DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public ArrayList<Article> getAllArticles(String title, String author, String key, String date) {

        try (Statement statement = this.connection.createStatement()) {
            ArrayList<Article> products = new ArrayList<Article>();
            StringBuilder query = new StringBuilder("SELECT * FROM articles");
            int i = 0;
            if (!title.equals("all") && !title.equals("")) {
                query.append(" where ");
                query.append("title='").append(title).append("'");
                i = 1;
            }
            if (!author.equals("all") && !author.equals("")) {
                if (i == 1) {
                    query.append(" and author='").append(author).append("'");
                } else {
                    query.append(" where ");
                    query.append("author='" + author + "'");
                    i = 1;
                }
            }
            if (!key.equals("all") && !key.equals("")) {
                if (i == 1) {
                    query.append(" and key_ LIKE '%" + key + "%'");
                } else {
                    query.append(" where ");
                    query.append("key_ LIKE '% %" + key + "% %'");
                    i = 1;
                }
            }
            if (!date.equals("all") && !date.equals("")) {
                if (i == 1) {
                    query.append(" and date_='" + date + "'");
                } else {
                    query.append(" where ");
                    query.append("date_='" + date + "'");
                    i = 1;
                }
            }
            System.out.println(query);
            query.append(";");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                products.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("key_"),
                        resultSet.getString("date_"),
                        resultSet.getString("owner")));
            }
            // Возвращаем наш список
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return new ArrayList<>();
        }
    }

    public Article getArticle(String id) {
        try (Statement statement = this.connection.createStatement()) {
            ArrayList<Article> products = new ArrayList<Article>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM articles where id =" + Integer.parseInt(id) + ";");
            while (resultSet.next()) {
                products.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("key_"),
                        resultSet.getString("date_"),
                        resultSet.getString("owner")));
            }
            // Возвращаем наш список
            return products.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return null;
        }
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS articles " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " title TEXT NOT NULL, " +
                " author TEXT NOT NULL, " +
                " key_ TEXT NOT NULL," +
                " date_ TEXT NOT NULL," +
                " owner TEXT NOT NULL );";

        PreparedStatement statemen = connection.prepareStatement(sql);
        statemen.execute();
        sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " username TEXT NOT NULL, " +
                " password TEXT NOT NULL);";
        statemen = connection.prepareStatement(sql);
        statemen.execute();
    }

    public int addArticle(Article article) {
        int t = -1;
        int val = new Random().nextInt(999999999);
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO articles(`id`, `title`, `author`, `key_`, `date_`, `owner`) " +
                        "VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, val);
            statement.setObject(2, article.getTitle());
            statement.setObject(3, article.getAuthor());
            statement.setObject(4, " " + article.getKeyWords() + " ");
            statement.setObject(5, article.getDate());
            statement.setObject(6, article.getOwner());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    // Удаление продукта по id
    public void deleteAll() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM articles;")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getArticles(String title, String author, String key, String date) {
        return 0;
    }

    public User getUser(User user) {
        try (Statement statement = this.connection.createStatement()) {
            User item = null;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users where username = '" + user.getUsername() + "'" +
                    " and password = '" + user.getPassword() + "';");
            while (resultSet.next()) {
                item = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            return item;
        } catch (Exception e) {
            // Если произошла ошибка - возвращаем пустую коллекцию
            return null;
        }
    }

    public void addUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(`username`, `password`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, user.getUsername());
            statement.setObject(2, user.getPassword());
            statement.execute();
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM articles where id = " + id + ";")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}