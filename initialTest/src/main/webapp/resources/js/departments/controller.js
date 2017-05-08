'use strict';
import jQuery from "jquery";
//import jqueryValidation from 'jquery-validation';
//const DepartmentService = require('./departmentService.js');
import "jquery-validation";
window.$ = window.jQuery = jQuery;
//const departmentService = new DepartmentService();
import DepartmentView from './view';
export default class DepartmentController {

    constructor(){
        this.departmentView = new DepartmentView;
    }

    showAllDepartments() {
        $.ajax({
            url: '/departments',
            type: 'GET'
        }).then((response) => {
            this.departmentView.displayDepartments(response);
        });
    };

    deleteDepartment(event) {
        $.ajax({
            url: '/delete',
            data: {id: event.target.name},
            type: 'POST'
        }).then((response) => {
            this.departmentView.displayDepartments(response);//departmentservice.
        });
    };

    updateDepartment(event) {
        $.ajax({
            url: '/update',
            data: {id: event.target.name},
            type: 'POST',
            dataType: 'json'
        }).then((response) => {
            this.departmentView.displayDepartmentDetails(response);//departmentservice.
        });
    };

    addDepartment(event) {
        let departmentId = event.target.name;
        $.ajax({
            url: '/update',
            data: {
                departmentId: departmentId
            },
            type: 'POST'
        }).then((response) => {
            this.departmentView.displayDepartmentDetails(response);//departmentservice.
        });
    };

    saveDepartment(event) {
        let depId = $('#id').val();
        let depName = $('#name').val();
        $.ajax({
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
        }).then((response) => {
            this.departmentView.displayDepartments(response);
        });
    };
}
