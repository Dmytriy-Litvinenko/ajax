package testtask.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController{ //implements PageController {

    /*@Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/errorPage.jsp").forward(request, response);
    }*/
    @RequestMapping(value = "/error", method = RequestMethod.GET)//
    public String showErrorPage() {
        return "errorPage";
    }
}

