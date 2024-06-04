package com.zhytelnyi.coursework.queueapp.util;

import com.zhytelnyi.coursework.queueapp.dao.QueueDao;
import com.zhytelnyi.coursework.queueapp.model.QueueModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseCreator {

    public static void createDatabaseCreator(QueueDao queueDao) {
        QueueModel test1 = new QueueModel("Черга до лікаря");

        queueDao.create(test1);
        ArrayList<String> arrayList = test1.getUsers();
        System.out.println(arrayList);
        arrayList.add("Hello");
        test1.setUsers(arrayList);

    }

}
