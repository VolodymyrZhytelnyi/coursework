package com.zhytelnyi.coursework.queueapp.dao;

import com.zhytelnyi.coursework.queueapp.model.QueueModel;

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
        if (queueModel != null) {
            queueModel.getUsers().add(userName);
        }
    }
}
