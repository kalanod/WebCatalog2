package com.example.webcatalog2.Model;

import java.util.Objects;

public class Article {
    int id;
    String author;
    String title;
    String keyWords;
    String date;
    String owner;

    public Article(int id, String title, String author, String keyWords, String date, String owner) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.keyWords = keyWords;
        this.date = date;
        this.owner = owner;
    }

    public Article(String title, String author, String keyWords, String date, String owner) {
        this.author = author;
        this.title = title;
        this.keyWords = keyWords;
        this.date = date;
        this.owner = owner;
    }
    public Article(String title, String author, String keyWords, String date) {
        this.author = author;
        this.title = title;
        this.keyWords = keyWords;
        this.date = date;
        this.owner = "Anonimus";
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(author, article.author) && Objects.equals(title, article.title) && Objects.equals(date, article.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, keyWords, date);
    }

    public String getOwner() {
        return owner;
    }
}
