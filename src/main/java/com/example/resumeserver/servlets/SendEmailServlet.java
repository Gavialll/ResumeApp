package com.example.resumeserver.servlets;

import com.example.resumeserver.dto.EmailDto;
import com.example.resumeserver.components.SendEmail;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendMessage", value = "/send-email")
public class SendEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            // Get information about email from request.
            request.setCharacterEncoding("UTF8");
            EmailDto message = new Gson().fromJson(request.getReader(), EmailDto.class);

            // Send email.
            new SendEmail()
                    .singIn("gavialviv@gmail.com", "rvsavlobbshewhji")
                    .to("andriudytko@gmail.com")
                    .send(message.getEmail(), message.getName(), message.getMessage());

            response.setStatus(200);
        }catch(IOException exception){
            response.setStatus(400);
        }
    }
}