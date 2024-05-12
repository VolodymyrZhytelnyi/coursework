package com.zhytelnyi.coursework.queueapp.util;

import com.zhytelnyi.coursework.queueapp.dao.QueueDao;
import com.zhytelnyi.coursework.queueapp.model.QueueModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseCreator {

    public static void createDatabaseCreator(QueueDao queueDao) {
        ArrayList<String> test = new ArrayList<>();
        test.add("Віталій");
        test.add("Степан");
        test.add("Анатолій");
        test.add("Володимир");
        queueDao.create(new QueueModel("Черга до лікаря", test));
        queueDao.create(new QueueModel("Черга до музею", test));
        queueDao.create(new QueueModel("Черга до здачі лаб", test));
    }

}
