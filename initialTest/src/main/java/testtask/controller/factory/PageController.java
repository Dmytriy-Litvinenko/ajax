package testtask.controller.factory;

import testtask.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface PageController {

    void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException;
}