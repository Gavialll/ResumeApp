package com.example.resumeserver.models.post;

import com.example.resumeserver.models.post.postDto.PostAddDto;
import com.example.resumeserver.models.post.postDto.PostUpdateDto;

import java.util.Date;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String code;
    private String description;
    private Date date;

    public Post() {
    }

    public Post(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Post adapter(PostUpdateDto post){
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setCode(post.getCode());
        post1.setDescription(post.getDescription());
        post1.setName(post.getName());
        return post1;
    }
    public static Post adapter(PostAddDto post){
        Post post1 = new Post();
        post1.setCode(post.getCode());
        post1.setDescription(post.getDescription());
        post1.setName(post.getName());
        return post1;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(name, post.name) && Objects.equals(code, post.code) && Objects.equals(description, post.description) && Objects.equals(date, post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, date);
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", name='" + name + '\'' + ", code='" + code + '\'' + ", description='" + description + '\'' + ", data=" + date + '}';
    }
}
