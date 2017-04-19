package testtask.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController{
    @RequestMapping(value = "/error", method = RequestMethod.GET)//
    public String showErrorPage() {
        return "errorPage";
    }
}

