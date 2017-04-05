package testtask.controller.factory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class FrontController extends HttpServlet {

    FactoryController factoryController = new  FactoryController();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getRequestURI();

        Controller controller = factoryController.getControllerByUrl(url);

        if (controller == null) {
            controller = factoryController.getDefaultController();
        }

        controller.goToPage(request, response);
    }
}