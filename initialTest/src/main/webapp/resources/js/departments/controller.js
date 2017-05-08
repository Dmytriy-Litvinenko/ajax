'use strict';
import jQuery from "jquery";
import DepartmentView from "./view";
import DepartmentService from "./service";
window.$ = window.jQuery = jQuery;

export default class DepartmentController {

    constructor() {
        this.departmentView = new DepartmentView();
        this.departmentService = new DepartmentService();
    }

    showAllDepartments() {
        this.departmentService.findAll()
            .then((response) => {
                this.departmentView.displayDepartments(response);
            });
    };

    deleteDepartment(event) {
        let id = event.target.name;
        this.departmentService.delete(id)
            .then((response) => {
                this.departmentView.displayDepartments(response);
            });
    };

    updateDepartment(event) {
        let id = event.target.name;
        this.departmentService.update(id)
            .then((response) => {
                this.departmentView.displayDepartmentDetails(response);
            });
    };

    addDepartment(event) {
        let departmentId = event.target.name;
        this.departmentService.add(departmentId)
            .then((response) => {
                this.departmentView.displayDepartmentDetails(response);
            });
    };

    saveDepartment(event) {
        let id = $('#id').val();
        let name = $('#name').val();
        this.departmentService.save(id, name)
            .then((response) => {
                this.departmentView.displayDepartments(response);
            });
    };
}
