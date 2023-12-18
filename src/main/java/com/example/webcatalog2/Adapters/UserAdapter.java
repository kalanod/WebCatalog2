package com.example.webcatalog2.Adapters;


import com.example.webcatalog2.db.DbHandler;
import com.example.webcatalog2.db.User;

import java.sql.SQLException;

public class UserAdapter {
    DbHandler dbHandler;
    public UserAdapter(){
        try {
            dbHandler = new DbHandler();
            dbHandler.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuthorized(User user) {
        return dbHandler.getUser(user) != null;
    }

    public User fillUser(User user) {
        return dbHandler.getUser(user);
    }

    public void add(User user) {
        dbHandler.addUser(user);
    }
}
