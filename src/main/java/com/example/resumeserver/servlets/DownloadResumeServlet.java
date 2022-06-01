package com.example.resumeserver.servlets;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Download", value = "/download")
public class DownloadResumeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();

        byte[] byteArray = Files.readAllBytes(Path.of("/Andrii Dutko Java Developer.pdf"));

        response.setContentType("application/vnd.pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "Andrii Dutko Java Developer.pdf" + "\"");
        out.write(byteArray);
        out.flush();
        out.close();
    }
}