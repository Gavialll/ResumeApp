package com.example.resumeserver.servlets;

import com.example.resumeserver.components.EmailDto;
import com.example.resumeserver.components.SendEmail;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "SendMessage", value = "/send-email")
public class SendEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            request.setCharacterEncoding("UTF8");
            EmailDto message = new Gson().fromJson(request.getReader(), EmailDto.class);

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
