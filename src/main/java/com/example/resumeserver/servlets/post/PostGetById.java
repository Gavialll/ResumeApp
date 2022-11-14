package com.example.resumeserver.servlets.post;

import com.example.resumeserver.models.post.Post;
import com.example.resumeserver.service.impl.PostServiceImpl;
import com.google.gson.Gson;
import org.postgresql.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet(name = "post_get_by_id", value = "/admin/post/get_by_id")
public class PostGetById extends HttpServlet {
    public PostGetById() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)  {
        try(PostServiceImpl postService = new PostServiceImpl()) {
            String id = req.getParameter("id");

            if(id != null) {
                Post post = postService.getById(Integer.parseInt(id));
                if(post != null) {
                    String json = new Gson().toJson(post);
                    Servlet.sendJson(response, json);
                } else {
                    response.setStatus(400);
                    Servlet.sendJson(response, String.format("Post with id = %s not found", id));
                }
            }
            else {
                response.setStatus(400);
                Servlet.sendJson(response, String.format("Post with id = %s not found", id));

            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
