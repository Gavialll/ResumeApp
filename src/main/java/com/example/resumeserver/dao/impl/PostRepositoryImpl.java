package com.example.resumeserver.dao.impl;

import com.example.resumeserver.components.SQL;
import com.example.resumeserver.dao.PostRepository;
import com.example.resumeserver.models.post.Post;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import static com.example.resumeserver.dao.DataBase.URL;
import static com.example.resumeserver.dao.DataBase.PASSWORD;
import static com.example.resumeserver.dao.DataBase.USER;

public class PostRepositoryImpl implements PostRepository, AutoCloseable {
    private Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
    private Statement st = connection.createStatement();
    private ResultSet rs = null;

    public PostRepositoryImpl() throws SQLException {
    }

    @Override
    public boolean add(Post post) {
        try {
            return st.executeUpdate(SQL.insert(post.getName(),post.getCode(),post.getDescription())) > 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Post post) {
        try {
            return st.executeUpdate(SQL.update(post.getId(), post.getName(),post.getCode(),post.getDescription())) > 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return st.executeUpdate(SQL.delete(id)) > 0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Post getById(int id) {
        Post post = new Post();
        try {
            rs = st.executeQuery(SQL.getById(id));
            rs.next();
            post.setId(rs.getInt("id"));
            post.setName(rs.getString("name"));
            post.setCode(rs.getString("code"));
            post.setDescription(rs.getString("description"));
            post.setDate(rs.getDate("date"));
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return post;
    }

    @Override
    public List<Post> getAll(int limit) {
        if(limit < 0) throw new IllegalArgumentException("Limit can not be less than 0");
        List<Post> postList = new LinkedList<>();
        try {
            if(limit == 0)
                rs = st.executeQuery(SQL.getAll());
            else
                rs = st.executeQuery(SQL.getAll(limit));
            while(rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setName(rs.getString("name"));
                post.setCode(rs.getString("code"));
                post.setDescription(rs.getString("description"));
                post.setDate(rs.getDate("date"));

                postList.add(post);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return postList;
    }

    @Override
    public void close() throws Exception {
        if(rs != null)  rs.close();
        st.close();
    }
}
