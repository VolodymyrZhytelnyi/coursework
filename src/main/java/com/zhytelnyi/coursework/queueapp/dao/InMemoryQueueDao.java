package com.zhytelnyi.coursework.queueapp.dao;

import com.zhytelnyi.coursework.queueapp.model.QueueModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class InMemoryQueueDao implements QueueDao{

    private TreeMap<Long, QueueModel> queueModels = new TreeMap<>();

    @Override
    public void create(QueueModel queueModel) {
        long id = queueModels.isEmpty()? 1 : queueModels.lastKey()+1;
        queueModel.setId(id);
        queueModels.put(id, queueModel);
    }

    @Override
    public Collection<QueueModel> findAll() {
        return queueModels.values();
    }

    @Override
    public QueueModel findById(Long id) {
        return queueModels.get(id);
    }

    @Override
    public void update(QueueModel queueModel) {
        queueModels.put(queueModel.getId(), queueModel);
    }

    @Override
    public void deleteById(Long id) {
        queueModels.remove(id);
    }

    @Override
    public void addUserToQueue(Long id, String userName) {
        QueueModel queueModel = findById(id);
        ArrayList<String> users = queueModel.getUsers();
        if (users.stream().anyMatch(existingUser -> existingUser.equalsIgnoreCase(userName))) {
            throw new RuntimeException("Користувач вже існує в черзі");
        }
        users.add(userName);
        queueModel.setUsers(users);
    }



    @Override
    public void nextUser(Long id) {
        QueueModel queueModel = findById(id);
        ArrayList<String> users = queueModel.getUsers();
        if (!users.isEmpty()) {
            users.remove(0);
        }
        queueModel.setUsers(users);
    }

    @Override
    public void deleteUserByName(Long id, String userName) {
        QueueModel queueModel = findById(id);
        ArrayList<String> users = queueModel.getUsers();

        users.removeIf(user -> user.equalsIgnoreCase(userName));

        queueModel.setUsers(users);
    }


    @Override
    public boolean checkProperty(Long id, String ownerName) {
        QueueModel queueModel = findById(id);
        String realTrueName = queueModel.getOwnerName();
        if (realTrueName != ownerName) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean isQueueAvailableForWrite(long id) {
        QueueModel queueModel = findById(id);

        return queueModel.isQueueWritable();
    }

    @Override
    public void setAccessQueue(long id, String command) {
        QueueModel queueModel = findById(id);

        if (command.equals("close")) {
            queueModel.setQueueWritable(false);
        } else if (command.equals("open")) {
            queueModel.setQueueWritable(true);
        } else {
            throw new IllegalArgumentException("Invalid command");
        }
    }



}
