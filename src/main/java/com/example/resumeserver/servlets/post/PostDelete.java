package com.example.resumeserver.servlets.post;

import com.example.resumeserver.service.impl.PostServiceImpl;
import org.postgresql.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "post_delete", value = "/admin/post/delete")
public class PostDelete extends HttpServlet {
    public PostDelete() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try(PostServiceImpl postService = new PostServiceImpl()) {
            String id = request.getParameter("id");
            if(id != null && postService.delete(Integer.parseInt(id))) {
                Servlet.sendJson(response, "true");
            }
            else {
                response.setStatus(400);
                Servlet.sendJson(response, String.format("Post with id = %s not found", id));
            }
        }catch(Exception e){
            response.setStatus(400);
            System.out.println(e.getMessage());
        }
    }
}
