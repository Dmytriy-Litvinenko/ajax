package testtask.controller.factory;


import testtask.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class FrontController extends HttpServlet {

    private FactoryController factoryController = new FactoryController();

    //@Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        Controller controller = factoryController.getControllerByUrl(url);
        if (controller == null) {
            controller = factoryController.getErrorPageController();
        }

        try {
            controller.goToPage(request, response);
        } catch (SQLException e) {
            try {
                throw new DAOException("you have DAO problems!!!");
            } catch (DAOException e1) {
                response.sendRedirect("/error");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse resp)
            throws ServletException, IOException {
        doService(servletRequest, resp);
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        doService(servletRequest, servletResponse);
    }/**/
}