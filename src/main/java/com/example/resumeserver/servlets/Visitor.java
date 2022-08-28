package com.example.resumeserver.servlets;

import com.example.resumeserver.components.SendEmail;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name = "add Visitor", value = "/addVisitor")
public class Visitor  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        ServletContext sc = request.getServletContext();
        String path = sc.getRealPath("/icon/Visitor.txt");

        addAndSend(path);
    }

    public static void addAndSend(String path){
        String message = Visitor.add(path);

        new SendEmail()
                .singIn("gavialviv@gmail.com", "rvsavlobbshewhji")
                .to("andriudytko@gmail.com")
                .send("", "Visitors statistics", message);
    }

    private static String add(String path) {
        String fileText = read(path);
        int startSubstring = fileText.indexOf(":");
        startSubstring++;

        int size = Integer.parseInt(fileText.substring(startSubstring).trim());
        size++;
        String message = "You have visitors: " + size;
        write(message, path);

        return message;
    }

    private static int get(String path) {
        String fileText = read(path);
        int index = fileText.indexOf(":");
        index++;
        return Integer.parseInt(fileText.substring(index).trim());
    }

    private static void write(String str, String path){
        File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file)){

            fileWriter.write(str);

        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    private static String read(String path){
        File file = new File(path);
        StringBuilder fileText = new StringBuilder();

        try(FileReader fileReader = new FileReader(file)){
            int i;
            do{
                i = fileReader.read();
                if(i != -1) fileText.append((char) i);
            } while(i != -1);
        }catch(IOException exception){
            exception.printStackTrace();
        }
        return fileText.toString();
    }
}
