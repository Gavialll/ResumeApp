package com.example.resumeserver.servlets.post;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet {
    private Servlet() {
    }
    public static void sendJson(HttpServletResponse response, String json) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }

    public static <T extends Object> T getObj(HttpServletRequest request, Class<T> obj) throws IOException {
        request.setCharacterEncoding("UTF8");
        return new Gson().fromJson(request.getReader(), obj);
    }
}
