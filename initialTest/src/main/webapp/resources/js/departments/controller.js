'use strict';
import jQuery from "jquery";
import DepartmentView from "./view";
import DepartmentService from "./service";
//import "jquery-validation";
window.$ = window.jQuery = jQuery;

export default class DepartmentController {

    constructor() {
        this.departmentView = new DepartmentView();
        this.departmentService = new DepartmentService();
    }

    showAllDepartments() {
        /*$.ajax({
         url: '/departments',
         type: 'GET'
         })*/
        this.departmentService.findAll()
            .then((response) => {
                this.departmentView.displayDepartments(response);
            });
    };

    deleteDepartment(event) {
        /*$.ajax({
         url: '/delete',
         data: {id: event.target.name},
         type: 'POST'
         })*/
        let id = event.target.name;
        this.departmentService.delete(id)
            .then((response) => {
                this.departmentView.displayDepartments(response);
            });
    };

    updateDepartment(event) {
        /*$.ajax({
         url: '/update',
         data: {id: event.target.name},
         type: 'POST',
         dataType: 'json'
         })*/
        let id = event.target.name;
        this.departmentService.update(id)
            .then((response) => {
                this.departmentView.displayDepartmentDetails(response);
            });
    };

    addDepartment(event) {
        let departmentId = event.target.name;
        /*$.ajax({
         url: '/update',
         data: {
         departmentId: departmentId
         },
         type: 'POST'
         })*/
        this.departmentService.add(departmentId)
            .then((response) => {
                this.departmentView.displayDepartmentDetails(response);
            });
    };

    saveDepartment(event) {
        let id = $('#id').val();
        let name = $('#name').val();
        /*$.ajax({
         headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
         },
         data: JSON.stringify({
         id: depId,
         name: depName
         }),
         url: '/saveDep',
         type: "POST"
         })*/
        this.departmentService.save(id, name)
            .then((response) => {
                this.departmentView.displayDepartments(response);
            });
    };
}
