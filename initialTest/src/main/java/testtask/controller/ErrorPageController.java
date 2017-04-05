package testtask.controller;


import testtask.controller.factory.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/error")
public class ErrorPageController implements Controller {//extends HttpServlet

    @Override
    //public void doGet
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/errorPage.jsp").forward(request, response);
    }
}

