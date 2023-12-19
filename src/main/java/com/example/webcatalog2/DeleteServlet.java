package com.example.webcatalog2;

import com.example.webcatalog2.Adapters.DataAdapter;
import com.example.webcatalog2.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.HttpRetryException;
@WebServlet("/del")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataAdapter.delete(req.getParameter("id"));
        File file= new File("../webapps/Web-catalog/"+req.getParameter("id") + ".pdf");
        file.delete();
        resp.sendRedirect(req.getContextPath());
    }
}
