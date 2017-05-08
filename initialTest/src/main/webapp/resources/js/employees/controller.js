'use strict';
import jQuery from "jquery";
import EmployeeView from "./view";
import EmployeeService from "./service";
window.$ = window.jQuery = jQuery;


export default class EmployeeController {

    constructor() {
        this.employeeViev = new EmployeeView();
        this.employeeService = new EmployeeService();
    }

    showAllEmployees(event) {
        let departmentId = event.target.name;
        this.employeeService.findAll(departmentId)
            .then((response) => {
                this.employeeViev.displayEmployees(response, departmentId);
            });
    };

    deleteEmployee(event) {
        let departmentId = $('#departmentId').val();
        let employeeId = event.target.name;
        this.employeeService._delete(employeeId)
            .then((response) => {
                this.employeeViev.displayEmployees(response, departmentId);
            });
    };

    updateEmployee(event) {
        let employeeId=event.target.name;
        this.employeeService.update(employeeId)
        .then((response) => {
            this.employeeViev.displayEmployeeDetails(response, response.department.id);
        });
    };

    addEmployee(event) {
        let departmentId = event.target.name;
        this.employeeService.add(departmentId)
        .then((response) => {
            this.employeeViev.displayEmployeeDetails(response, departmentId);
        });
    };

    saveEmployee() {
        let empId = $('#id').val();
        let empName = $('#name').val();
        let empEmail = $('#email').val();
        let empSalary = $('#salary').val();
        let empBirthDate = $('#birthDate').val();
        let departmentId = $('#departmentId').val();
        let employee = {
            id: empId,
            name: empName,
            email: empEmail,
            salary: empSalary,
            birthDate: empBirthDate,
            department: {}
        };
        this.employeeService.save(employee,departmentId)
        .then((data) => {
            this.employeeViev.displayEmployees(data, departmentId);
        });
    };
}
