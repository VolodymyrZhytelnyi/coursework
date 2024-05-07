package com.zhytelnyi.coursework.queueapp.controllers;

import java.io.*;
import java.util.Collection;

import com.zhytelnyi.coursework.queueapp.dao.QueueDao;
import com.zhytelnyi.coursework.queueapp.model.QueueModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "QueueController", urlPatterns = {"/queueModels", "/index.jsp"})
public class QueueController extends HttpServlet {

    private QueueDao queueDao;

    @Override
    public void init() throws ServletException {
        super.init();
        queueDao = (QueueDao)getServletContext().getAttribute("queueDao");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<QueueModel> queueModels = queueDao.findAll();
        request.setAttribute("queueModels", queueModels);
        request.getRequestDispatcher("WEB-INF/jsp/dashboard.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }



}
