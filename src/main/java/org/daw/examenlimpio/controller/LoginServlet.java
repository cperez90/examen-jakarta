package org.daw.examenlimpio.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = "1234";

        if (!Objects.equals(req.getParameter("password"), password)) {
            resp.sendRedirect("login.jsp");
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",username);

        session.setMaxInactiveInterval(60);

        resp.sendRedirect("decks");
    }
}
