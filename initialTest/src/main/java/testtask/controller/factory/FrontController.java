package testtask.controller.factory;


import testtask.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class FrontController extends HttpServlet {

    private FactoryController factoryController = new FactoryController();

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        Controller controller = factoryController.getControllerByUrl(url);
        if (controller == null) {
            controller = factoryController.getErrorPageController();
        }
        try {
            controller.goToPage(request, response);
        } catch (DAOException e) {
            response.sendRedirect("/error");
        }
    }



    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse resp)
            throws ServletException, IOException {
        doService(servletRequest, resp);
    }

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        doService(servletRequest, servletResponse);
    }/**/
}