'use strict';
import jQuery from "jquery";
//import "jquery-validation";
//const departmentService = new DepartmentService();
window.$ = window.jQuery = jQuery;

import EmployeeView from "./view";

export default class EmployeeController {

    constructor() {
        this.employeeViev = new EmployeeView();
    }

    showAllEmployees(event) {
        let departmentId = event.target.name;
        $.ajax({
            url: '/employees',
            type: 'GET',
            data: {departmentId: departmentId}
        }).then((response) => {
            this.employeeViev.displayEmployees(response, departmentId);
        });
    };

    deleteEmployee(event) {
        let departmentId = $('#departmentId').val();
        $.ajax({
            url: '/empDelete',
            data: {employeeId: event.target.name},
            type: 'POST'
        }).then((response) => {
            this.employeeViev.displayEmployees(response, departmentId);
        });
    };

    updateEmployee(event) {
        $.ajax({
            url: '/employeeUpdate',
            data: {
                employeeId: event.target.name,
                departmentId: ""
            },
            type: 'POST'
        }).then((response) => {
            this.employeeViev.displayEmployeeDetails(response, response.department.id);
        });
    };

    addEmployee(event) {
        let departmentId = event.target.name;
        $.ajax({
            url: '/employeeUpdate',
            data: {
                employeeId: "",
                departmentId: departmentId
            },
            type: 'POST'
        }).then((response) => {
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
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(employee),
            dataType: "json",
            url: '/employeeSave?departmentId=' + departmentId,
            type: "POST"

        }).then((data) => {
            this.employeeViev.displayEmployees(data, departmentId);
        });
    };
}
