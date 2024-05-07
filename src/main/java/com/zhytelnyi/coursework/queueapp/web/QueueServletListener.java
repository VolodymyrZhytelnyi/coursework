package com.zhytelnyi.coursework.queueapp.web;

import java.io.*;

import com.zhytelnyi.coursework.queueapp.dao.InMemoryQueueDao;
import com.zhytelnyi.coursework.queueapp.dao.QueueDao;
import com.zhytelnyi.coursework.queueapp.util.DatabaseCreator;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class QueueServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing context...");
        QueueDao queueDao = new InMemoryQueueDao();
        DatabaseCreator.createDatabaseCreator(queueDao);
        sce.getServletContext().setAttribute("queueDao", queueDao);
        System.out.println("Context initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
