package com.example.resumeserver.servlets.post;

import com.example.resumeserver.models.post.postDto.PostAddDto;
import com.example.resumeserver.models.post.Post;
import com.example.resumeserver.service.impl.PostServiceImpl;
import org.postgresql.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "post_add", value = "/admin/post/add")
public class PostAdd extends HttpServlet {
    public PostAdd() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(PostServiceImpl postService = new PostServiceImpl()) {
            PostAddDto post = Servlet.getObj(request, PostAddDto.class);

            if(postService.add(Post.adapter(post))){
                Servlet.sendJson(response, "true");
            }
            else {
                response.setStatus(400);
            }

        }catch(Exception e){
            response.setStatus(400);
            Servlet.sendJson(response, "No post added");
        }
    }
}
