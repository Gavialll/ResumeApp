package com.example.resumeserver.components;


public class SQL {

    public static String delete(int id){
        return String.format("%s %d","delete from posts where id =", id);
    }

    public static String insert(String name, String code, String description){
        return String
                .format("INSERT INTO posts (name, code, description, date) VALUES ('%s', '%s', '%s', Now())",
                        name, code, description);
    }

    public static String getById(int id){
        return String.format("%s %d","select * from posts where id =", id);
    }

    public static String getAll(){
        return "select * from posts";
    }

    public static String getAll(int limit){
        return String.format("%s %d","select * from posts limit", limit);
    }

    public static String update(int id, String name, String code, String description) {
        return String
                .format("update posts set id = %d, name = '%s', code = '%s', description = '%s', date = Now() where id = %d",
                       id, name, code, description, id);
    }

    private SQL() {
    }
}
