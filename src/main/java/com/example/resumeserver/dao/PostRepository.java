package com.example.resumeserver.dao;

import com.example.resumeserver.models.post.Post;

import java.util.List;

public interface PostRepository extends AutoCloseable {
    boolean add(Post post) throws ClassNotFoundException;
    boolean update(Post post);
    boolean delete(int id);
    Post getById(int id);
    List<Post> getAll(int limit);
}
