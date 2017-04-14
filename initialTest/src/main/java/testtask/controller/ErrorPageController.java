package testtask.controller;


import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller(value = "/error")
public class ErrorPageController implements PageController {

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/errorPage.jsp").forward(request, response);
    }
}

