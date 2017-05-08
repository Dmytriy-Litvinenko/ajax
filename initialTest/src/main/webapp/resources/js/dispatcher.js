'use strict';
import jQuery from "jquery";
//import jqueryValidation from 'jquery-validation';
//const DepartmentService = require('./departmentService.js');
import EmployeeController from "./employees/controller.js";
import DepartmentController from "./departments/controller.js";
window.$ = window.jQuery = jQuery;

export default class Dispatcher {

    constructor() {
        this.departmentController = new DepartmentController();
        this.employeeController = new EmployeeController();
        this.map = new Map();
        this.map.set('showAllDepartments', () => this.departmentController.showAllDepartments());
        this.map.set('deleteDepartment', () => this.departmentController.deleteDepartment(event));
        this.map.set('addDepartment', () => this.departmentController.addDepartment(event));
        this.map.set('updateDepartment', () => this.departmentController.updateDepartment(event));
        this.map.set('saveDepartment', () => this.departmentController.saveDepartment(event));
        this.map.set('showAllEmployees', () => this.employeeController.showAllEmployees(event));
        this.map.set('deleteEmployee', () => this.employeeController.deleteEmployee(event));
        this.map.set('addEmployee', () => this.employeeController.addEmployee(event));
        this.map.set('updateEmployee', () => this.employeeController.updateEmployee(event));
    }

    listen() {
        $('body').on('click', '.listener', (event) => {
            let valueFromEvent = event.target.value;
            this.map.get(valueFromEvent)(event);
        });
    }


}