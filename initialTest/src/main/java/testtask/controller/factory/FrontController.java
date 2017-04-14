/*package testtask.controller.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.HttpRequestHandler;
import testtask.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class FrontController implements HttpRequestHandler {

    @Autowired
    private ApplicationContext applicationContext;//= new ClassPathXmlApplicationContext("application_context.xml");

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        PageController controller;
        try {
            controller = applicationContext.getBean(url, PageController.class);
        } catch (NoSuchBeanDefinitionException e) {
            controller = applicationContext.getBean("/error", PageController.class);
        }
        try {
            controller.goToPage(request, response);
        } catch (DAOException e) {
            e.getCause().printStackTrace(System.err);
            response.sendRedirect("/error");
        }
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doService(httpServletRequest, httpServletResponse);
    }
}*/