package com.zhytelnyi.coursework.queueapp.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import com.zhytelnyi.coursework.queueapp.dao.QueueDao;
import com.zhytelnyi.coursework.queueapp.model.QueueModel;
import com.zhytelnyi.coursework.queueapp.model.User;
import com.zhytelnyi.coursework.queueapp.util.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "QueueController", urlPatterns = {"/queueController", "/index.jsp"})
public class QueueController extends HttpServlet {

    private QueueDao queueDao;
    private Session session;

    @Override
    public void init() throws ServletException {
        super.init();
        queueDao = (QueueDao)getServletContext().getAttribute("queueDao");
        session = (Session)getServletContext().getAttribute("session");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<QueueModel> queueModels = queueDao.findAll();
        request.setAttribute("queueModels", queueModels);
        request.getRequestDispatcher("WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        User user = session.getUser();
//        if (user.ownsQueue()) {
//            user.editQueue();
//        } else {
//            System.out.println("Ви не володієте жодною чергою.");
//        }

        String command = request.getParameter("command");
        if (command == null) {
            response.sendError(400, "parameter command was not found");
            return;
        }
        switch (command) {
            case "delete":
                delete(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "manage":
                manage(request, response);
                break;
            case "addUser":
                addUser(request, response);
                break;
            case "nextUser":
                nextUser(request, response);
                break;
            case "deleteUserByName":
                deleteUserByName(request, response);
                break;
            case "writeControl":
                writeControl(request, response);
                break;
            default:
                response.sendError(400, "wrong command");
        }
    }

    private void writeControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!authenticateAndAuthorize(request, response)) {
            return;
        }
        String command = request.getParameter("value");
        Long id = Long.valueOf(request.getParameter("id"));
        queueDao.setAccessQueue(id, command);



        response.sendRedirect("index.jsp");

    }

    private void deleteUserByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!authenticateAndAuthorize(request, response)) {
            return;
        }
        String name = request.getParameter("name");
        Long id = Long.valueOf(request.getParameter("id"));
        queueDao.deleteUserByName(id, name);
        response.sendRedirect("index.jsp");
    }

    private void nextUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!authenticateAndAuthorize(request, response)) {
            return;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        queueDao.nextUser(id);
        response.sendRedirect("index.jsp");
    }


    private void manage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        try {
            request.getSession().setAttribute("id", id);
            request.getRequestDispatcher("WEB-INF/jsp/manageQueue.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));

        if (!queueDao.isQueueAvailableForWrite(id)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userName = request.getParameter("userName");
        System.out.println(userName);

        if (userName == null) {
            try {
                System.out.println("check1");
                request.getSession().setAttribute("id", id);
                request.getRequestDispatcher("WEB-INF/jsp/addUserToQueue.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            queueDao.addUserToQueue(id, userName);
            response.sendRedirect("index.jsp");
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        if (name == null) {
            try {
                // Check if the user is authenticated
                if (!request.authenticate(response)) {
                    // If the user is not authenticated, stop processing the request
                    return;
                }
                // Check if the user has the "queue_owner" role
                if (!request.isUserInRole("queue_owner")) {
                    // If the user does not have the "queue_owner" role, send a 403 Forbidden error
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                // If the user is authenticated and has the "queue_owner" role, forward the request to createQueue.jsp
                request.getRequestDispatcher("WEB-INF/jsp/createQueue.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            String username = request.getUserPrincipal().getName();
            queueDao.create(new QueueModel(name, username));
            response.sendRedirect("index.jsp");
        }
    }




    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (!authenticateAndAuthorize(request, response)) {
                return;
            }
            Long id = Long.valueOf(request.getParameter("id"));
            queueDao.deleteById(id);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.sendError(400);
        }
    }


    public void createQueue() {
        User user = session.getUser();
//        user.createQueue();
    }

    private boolean authenticateAndAuthorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (!request.authenticate(response)) {
                return false;
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        if (!request.isUserInRole("queue_owner")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        String username = request.getUserPrincipal().getName();
        Long id = Long.valueOf(request.getParameter("id"));

        Boolean check = queueDao.checkProperty(id, username);
        if (!check) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }



}
