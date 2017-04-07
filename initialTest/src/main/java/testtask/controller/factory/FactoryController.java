package testtask.controller.factory;


import testtask.controller.ErrorPageController;
import testtask.controller.departments.DepartmentDelController;
import testtask.controller.departments.DepartmentSaveController;
import testtask.controller.departments.DepartmentUpdateController;
import testtask.controller.departments.DepartmentsAllController;
import testtask.controller.employees.EmployeeDelController;
import testtask.controller.employees.EmployeeSaveController;
import testtask.controller.employees.EmployeeUpdateController;
import testtask.controller.employees.EmployeesAllController;

import java.util.HashMap;
import java.util.Map;

public class FactoryController {

    private Map<String, Controller> controllerMap = new HashMap<>();
    private Controller errorPageController = new ErrorPageController();

    {
        controllerMap.put("/", new MainController());
        controllerMap.put("/departments", new DepartmentsAllController());
        controllerMap.put("/depUpdate", new DepartmentUpdateController());
        controllerMap.put("/depSave", new DepartmentSaveController());
        controllerMap.put("/depDelete", new DepartmentDelController());
        controllerMap.put("/employees", new EmployeesAllController());
        controllerMap.put("/employeeUpdate", new EmployeeUpdateController());
        controllerMap.put("/employeeSave", new EmployeeSaveController());
        controllerMap.put("/empDelete", new EmployeeDelController());
        controllerMap.put("/error", new ErrorPageController());
    }

    public Controller getControllerByUrl(String url) {
        return controllerMap.get(url);
    }

    public Controller getErrorPageController() {
        return errorPageController;
    }

}
