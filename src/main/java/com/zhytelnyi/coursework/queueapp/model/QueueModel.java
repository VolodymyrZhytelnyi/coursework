package com.zhytelnyi.coursework.queueapp.model;


import java.util.ArrayList;

public class QueueModel {
    Long id;
    String name;
    ArrayList<String> users;


    public QueueModel(Long id, String name, ArrayList<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public QueueModel(String name, ArrayList<String> users) {
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
