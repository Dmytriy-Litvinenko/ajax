'use strict';
import jQuery from "jquery";
//const DepartmentController = require('./departments/controller.js');
import DepartmentController from './departments/controller.js';
import EmployeeController from './employees/controller.js';
//const EmployeeController = require('./employees/controller.js');
import Dispatcher from './dispatcher';
window.$ = window.jQuery = jQuery;
$(document).ready(function () {

    /*let departmentController = new DepartmentController();
    let employeeController = new EmployeeController();*/
    let dispatcher = new Dispatcher();
    dispatcher.listen();

    /*let dispatcher = new Map();

    dispatcher.set('showAllDepartments', () => departmentController.showAllDepartments());
    dispatcher.set('deleteDepartment', () => departmentController.deleteDepartment(event));
    dispatcher.set('addDepartment', () => departmentController.addDepartment(event));
    dispatcher.set('updateDepartment', () => departmentController.updateDepartment(event));
    dispatcher.set('showAllEmployees', () => employeeController.showAllEmployees(event));
    dispatcher.set('deleteEmployee', () => employeeController.deleteEmployee(event));
    dispatcher.set('addEmployee', () => employeeController.addEmployee(event));
    dispatcher.set('updateEmployee', () => employeeController.updateEmployee(event));

    $('body').on('click', '.listener', (event) => {//
        let valueFromEvent = event.target.value;
        dispatcher.get(valueFromEvent)(event);
    });*/
});


/*
 var contr = new Main();
 contr.showHeader();

 var service = new Service();

 var department = new Department();
 window.GlobDep = department;

 department.viewDepartmentsList();


 var Ingredients = {
 DepAll: function () {
 department.viewDepartmentsList();
 },
 DelDep: function () {
 department.DelDep(event)
 },
 AddDep: function () {
 department.viewDepartment();
 }

 };


 $('body').on("click", ".listen", function (event) {
 var value = $(this).attr('value');
 Ingredients[value](event);
 });

 */