package testtask.controller.factory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class FrontController extends HttpServlet {

    private FactoryController factoryController = new FactoryController();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        Controller controller = factoryController.getControllerByUrl(url);
        if (controller == null) {
            controller = factoryController.getErrorPageController();
        }

        controller.goToPage(request, response);
    }
}