package com.example.resumeserver.servlets;

import com.example.resumeserver.components.EmailDto;
import com.example.resumeserver.components.LocationDto;
import com.example.resumeserver.components.SendEmail;
import com.example.resumeserver.jdbc.DataBase;
import com.google.gson.Gson;
import org.postgresql.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(name = "add Visitor", value = "/addVisitor")
public class Visitor  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int count = 0;
        try {
            DataBase dataBase = new DataBase();
            count = dataBase.getCount();
            count++;
            dataBase.setCount(count);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            request.setCharacterEncoding("UTF8");
            LocationDto location = new Gson().fromJson(request.getReader(), LocationDto.class);

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
