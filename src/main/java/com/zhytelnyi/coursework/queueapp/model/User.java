package com.zhytelnyi.coursework.queueapp.model;

import com.zhytelnyi.coursework.queueapp.dao.InMemoryQueueDao;

public class User {
    private String username;
    private String password;
    // інші властивості...

    private Long ownedQueueId;



    public void createQueue(InMemoryQueueDao queueDao) {
//        QueueModel newQueue = new QueueModel();
//        queueDao.create(newQueue);
//        this.ownedQueueId = newQueue.getId();
    }

    public void editQueue(QueueModel queue) {
        if (this.ownedQueueId.equals(queue.getId())) {
            // редагування черги...
        } else {
            System.out.println("Ви не володієте цією чергою.");
        }
    }
}

