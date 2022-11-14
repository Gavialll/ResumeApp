package com.example.resumeserver.servlets.post;

import com.example.resumeserver.models.post.postDto.PostUpdateDto;
import com.example.resumeserver.models.post.Post;
import com.example.resumeserver.service.impl.PostServiceImpl;
import org.postgresql.Driver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "post_update", value = "/admin/post/update")
public class PostUpdate extends HttpServlet {
    public PostUpdate() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try(PostServiceImpl postService = new PostServiceImpl()) {
            PostUpdateDto post = Servlet.getObj(request, PostUpdateDto.class);

            if(postService.update(Post.adapter(post))){
                Servlet.sendJson(response, "true");
            }
            else {
                response.setStatus(400);
                Servlet.sendJson(response, String.format("Post with id = %s not found", post.getId()));
            }

        }catch(Exception e){
            response.setStatus(400);
        }
    }
}
