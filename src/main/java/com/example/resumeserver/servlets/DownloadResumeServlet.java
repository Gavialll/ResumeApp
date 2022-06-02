package com.example.resumeserver.servlets;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Download", value = "/download")
public class DownloadResumeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        ServletOutputStream out = response.getOutputStream();
        ServletContext sc = request.getServletContext();
        String absolutePathTomcat = sc.getRealPath("/icon/Andrii Dutko Java Developer.pdf");
        System.out.println(absolutePathTomcat);

        File fileRes = new File(absolutePathTomcat);

        FileInputStream file = new FileInputStream(fileRes);

        byte[] byteArray = file.readAllBytes();

        response.setContentType("application/vnd.pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "Andrii Dutko Java Developer.pdf" + "\"");
        out.write(byteArray);
        out.flush();
        out.close();
    }
}