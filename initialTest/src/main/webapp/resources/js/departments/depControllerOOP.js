//export default
'use strict';
class DepartmentController{

    constructor(){
        this.departmentservice = new DepartmentService();
    }

    showAllDepartments () {
        $.ajax({
            url: '/departments',
            type: 'GET'
        }).then((response) => {
            this.departmentservice.displayDepartments(response);
            //displayDepartments()
        });
    };

    deleteDepartment (event) {
        $.ajax({
            url: '/delete',
            data: {id: event.target.name},
            type: 'POST'
        }).then((response) => {
            this.departmentservice.displayDepartments(response);
            //displayDepartments(response);
        });
    };

    updateDepartment (event) {
        $.ajax({
            url: '/update',
            data: {id: event.target.name},
            type: 'POST',
            dataType: 'json'
        }).then((response) => {
            this.departmentservice.displayDepartmentDetails(response);
        });
    };

    addDepartment (event) {
        let departmentId = event.target.name;
        $.ajax({
            url: '/update',
            data: {
                departmentId: departmentId
            },
            type: 'POST'
        }).then((response) => {
            this.departmentservice.displayDepartmentDetails(response);
        });
    };

    saveDepartment () {
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
        }).then(() => {
            this.departmentservice.showAllDepartments();
        });
    };


    //let departmentService = new DepartmentService();

    //let departmentController = new DepartmentController();

    //window.GlobDep = department;

    //department.viewDepartmentsList();



};/*
module.exports = {
    departmentController: DepartmentController

};*/
//export default DepartmentController;