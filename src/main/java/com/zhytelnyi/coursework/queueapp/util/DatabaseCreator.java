package com.zhytelnyi.coursework.queueapp.util;

import com.zhytelnyi.coursework.queueapp.dao.QueueDao;
import com.zhytelnyi.coursework.queueapp.model.QueueModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseCreator {

    public static void createDatabaseCreator(QueueDao queueDao) {
        ArrayList<String> test = new ArrayList<>();
        test.add("Hello");
        test.add("djsf");
        queueDao.create(new QueueModel("Hello", test));
    }

}
