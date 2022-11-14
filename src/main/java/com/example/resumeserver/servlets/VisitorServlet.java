package com.example.resumeserver.servlets;

import com.example.resumeserver.dto.LocationDto;
import com.example.resumeserver.components.SendEmail;
import com.example.resumeserver.DataBase;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "add Visitor", value = "/addVisitor")
public class VisitorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        try {
            // Add one visitor.
            DataBase dataBase = new DataBase();
            count = dataBase.getCount();
            count++;
            dataBase.setCount(count);
        } catch(SQLException e) {
            System.out.println("No connect to DataBase");
        }
        try {
            // Get location with request.
            request.setCharacterEncoding("UTF8");
            LocationDto location = new Gson().fromJson(request.getReader(), LocationDto.class);

            // Send message with location.
            new SendEmail()
                    .singIn("gavialviv@gmail.com", "rvsavlobbshewhji")
                    .to("andriudytko@gmail.com")
                    .send("",
                            String.format("%s %d",
                                    "You have visitors:",
                                    count),
                            String.format("%s %s %s",
                                    location.getCountry(),
                                    location.getCity(),
                                    location.getStreet()));
            response.setStatus(200);
        }catch(IOException exception){
            response.setStatus(400);
        }
    }
}
