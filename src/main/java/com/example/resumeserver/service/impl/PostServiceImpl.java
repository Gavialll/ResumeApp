package com.example.resumeserver.service.impl;

import com.example.resumeserver.dao.impl.PostRepositoryImpl;
import com.example.resumeserver.models.post.Post;
import com.example.resumeserver.service.PostService;
import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements PostService, AutoCloseable {
    private PostRepositoryImpl postRepository = new PostRepositoryImpl();

    public PostServiceImpl() throws SQLException {
    }

    @Override
    public boolean add(Post post) {
        return postRepository.add(post);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public boolean delete(int id) {
        return postRepository.delete(id);
    }

    @Override
    public Post getById(int id) {
        return postRepository.getById(id);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.getAll(0);
    }

    public List<Post> getAll(int limit) {
        return postRepository.getAll(limit);
    }

    @Override
    public void close() throws Exception {
        postRepository.close();
    }
}
