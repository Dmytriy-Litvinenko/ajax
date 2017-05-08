'use strict';
import jQuery from "jquery";
//const User = require('./user.js');
const DepartmentController = require('./departments/depControllerOOP.js');
window.$ = window.jQuery = jQuery;
//window.document = document;
$(document).ready(function () {

    let departmentController = new DepartmentController();
    let dispatcher = new Map();
    dispatcher.set('showAllDepartments',()=>departmentController.showAllDepartments());
    dispatcher.set('deleteDepartment',()=>departmentController.deleteDepartment(event));
    dispatcher.set('addDepartment',()=>departmentController.addDepartment(event));
    dispatcher.set('updateDepartment',()=>departmentController.updateDepartment(event));
    dispatcher.set('saveDepartment',()=>departmentController.saveDepartment(event));
    //dispatcher.set('showAllDepartments',()=>departmentController.showAllDepartments());

    $('body').on('click', '.listener',(event)=> {//
        //let value = $(this).attr('value');
        let valueFromEvent=event.target.value;
        dispatcher.get(valueFromEvent)(event);
    });
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