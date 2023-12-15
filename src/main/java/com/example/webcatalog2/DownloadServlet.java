package com.example.webcatalog2;

import com.example.webcatalog2.Adapters.DataAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName =  DataAdapter.getArticle(req.getParameter("id")).getTitle() + " - " + DataAdapter.getArticle(req.getParameter("id")).getAuthor();
        String fileType = "application/pdf";
        resp.setContentType(fileType);
        resp.setHeader("Content-disposition","attachment; filename="+fileName+".pdf");
        // Assume file name is retrieved from database
        // For example D:\\file\\test.pdf
        File my_file = new File("../webapps/Web-catalog/" + req.getParameter("id") + ".pdf");
        // This should send the file to browser
        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
