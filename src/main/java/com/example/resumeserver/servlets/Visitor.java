package com.example.resumeserver.servlets;

import com.example.resumeserver.components.SendEmail;
import com.example.resumeserver.jdbc.DataBase;
import org.postgresql.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "add Visitor", value = "/addVisitor")
public class Visitor  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try {
            DataBase dataBase = new DataBase();
            count = dataBase.getCount();
            count++;
            dataBase.setCount(count);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        new SendEmail()
                .singIn("gavialviv@gmail.com", "rvsavlobbshewhji")
                .to("andriudytko@gmail.com")
                .send("", 
                        "Visitors statistics",
                        "You have visitors: " + count);
    }
}
