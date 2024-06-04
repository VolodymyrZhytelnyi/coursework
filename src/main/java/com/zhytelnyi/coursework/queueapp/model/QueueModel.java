package com.zhytelnyi.coursework.queueapp.model;


import java.util.ArrayList;

public class QueueModel {
    private Long id;
    private String name;
    private ArrayList<String> users;

    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public QueueModel(Long id, String name, ArrayList<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public QueueModel(String name, ArrayList<String> users) {
        this.name = name;
        this.users = users;
    }

    public QueueModel(String name) {
        this.name = name;
        this.users = new ArrayList<>();
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
