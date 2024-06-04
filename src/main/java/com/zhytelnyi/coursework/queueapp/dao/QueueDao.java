package com.zhytelnyi.coursework.queueapp.dao;

import com.zhytelnyi.coursework.queueapp.model.QueueModel;

import java.util.Collection;

public interface QueueDao {
    void create(QueueModel queueModel);
    QueueModel findById(Long id);
    Collection<QueueModel> findAll();
    void update(QueueModel queueModel);
    void deleteById(Long id);
    void addUserToQueue(Long queueId, String userName);
    void nextUser(Long id);
    void deleteUserByName(Long id, String userName);

}
