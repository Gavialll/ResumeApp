package com.example.resumeserver.models.post.postDto;

public class PostAddDto {
    private String name;
    private String code;
    private String description;

    public PostAddDto(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public PostAddDto() {
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

    @Override
    public String toString() {
        return "PostAddDto{" + "name='" + name + '\'' + ", code='" + code + '\'' + ", description='" + description + '\'' + '}';
    }
}
