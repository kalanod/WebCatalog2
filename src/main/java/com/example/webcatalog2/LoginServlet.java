package com.example.webcatalog2;

import com.example.webcatalog2.Adapters.UserAdapter;
import com.example.webcatalog2.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(0, req.getParameter("username"),
                req.getParameter("password"));
        UserAdapter adapter = new UserAdapter();
        if (!adapter.isAuthorized(user)) {
            adapter.add(user);
        }
        user = adapter.fillUser(user);
        req.getSession().setAttribute("User", user);
        resp.sendRedirect(req.getContextPath());
    }
}
