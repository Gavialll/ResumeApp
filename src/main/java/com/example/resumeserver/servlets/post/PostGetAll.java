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
import java.util.List;

@WebServlet(name = "post_get_all", value = "/admin/post/get_all")
public class PostGetAll extends HttpServlet {
    public PostGetAll() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)  {
        try(PostServiceImpl postService = new PostServiceImpl()) {
            String limit = req.getParameter("limit");
            List<Post> postList =
                    limit == null
                    ? postService.getAll()
                    : postService.getAll(Integer.parseInt(limit));

            String json = new Gson().toJson(postList);

            Servlet.sendJson(response, json);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
