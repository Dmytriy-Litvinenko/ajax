package testtask.util;

import testtask.model.Department;
import testtask.model.Employee;

import java.util.List;
import java.util.Map;

public class JsonObject {

    private String status;

    private Map<String,String> errors;

    private List<Department> departments;

    private List<Employee> employees;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }



    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

