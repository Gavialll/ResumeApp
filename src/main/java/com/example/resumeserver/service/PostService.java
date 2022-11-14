package com.example.resumeserver.service;

import com.example.resumeserver.models.post.Post;

import java.util.List;

public interface PostService extends AutoCloseable {
    boolean add(Post post);
    boolean update(Post post);
    boolean delete(int id);
    Post getById(int id);
    List<Post> getAll();
    List<Post> getAll(int limit);
}
