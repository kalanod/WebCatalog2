package com.example.webcatalog2;

import com.example.webcatalog2.Adapters.DataAdapter;
import com.example.webcatalog2.db.DbHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


import java.io.*;
import java.nio.file.Paths;

@WebServlet(name = "helloServlet", value = "/add")
@MultipartConfig

public class AddServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        if (!fileName.split("\\.")[1].equals("pdf")){
            resp.sendRedirect(req.getContextPath() + "/index");
            return;
        }
        InputStream fileContent = filePart.getInputStream();
        System.out.println(System.getProperty("user.dir"));
        int id = DataAdapter.saveFile(
                req.getParameter("title"),
                req.getParameter("author"),
                req.getParameter("key"),
                req.getParameter("date"));
        try (OutputStream output = new FileOutputStream(new File("../webapps/Web-catalog/"+id + ".pdf"))) {
            fileContent.transferTo(output);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/index");

    }
}