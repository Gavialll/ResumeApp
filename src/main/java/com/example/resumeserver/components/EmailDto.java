package com.example.resumeserver.components;

public class EmailDto {
    private String name;
    private String email;
    private String message;

    public EmailDto(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "EmailDto{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", message='" + message + '\'' + '}';
    }
}
