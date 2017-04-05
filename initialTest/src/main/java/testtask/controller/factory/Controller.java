package testtask.controller.factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}