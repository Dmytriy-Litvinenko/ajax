'use strict';
import jQuery from "jquery";
//const DepartmentService = require('./departmentService.js');
window.$ = window.jQuery = jQuery;
//const departmentService = new DepartmentService();

class DepartmentController {

    showAllDepartments() {
        $.ajax({
            url: '/departments',
            type: 'GET'
        }).then((response) => {
            this.displayDepartments(response);//departmentservice.
            //displayDepartments()
        });
    };

    deleteDepartment(event) {
        $.ajax({
            url: '/delete',
            data: {id: event.target.name},
            type: 'POST'
        }).then((response) => {
            this.displayDepartments(response);//departmentservice.
            //displayDepartments(response);
        });
    };

    updateDepartment(event) {
        $.ajax({
            url: '/update',
            data: {id: event.target.name},
            type: 'POST',
            dataType: 'json'
        }).then((response) => {
            this.displayDepartmentDetails(response);//departmentservice.
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
            this.displayDepartmentDetails(response);//departmentservice.
        });
    };

    saveDepartment() {
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
            this.showAllDepartments();//this.departmentservice.
        });
    };


    displayDepartments = (response) => {
        //this;
        let body = $('body');
        body.text('');
        let table = $('<table>');
        table.append($('<tr>').append($('<td>').append($('<b>').text('Department Name'))));
        for (let i = 0; i < response.length; i++) {
            table.append(
                $('<tr>')
                    .append($('<td>').text(response[i].name))
                    .append($('<td>').append($('<button onclick="this.deleteDepartment(event);" name="' + response[i].id + '">Delete</button>')))
                    .append($('<td>').append($('<button onclick="this.updateDepartment(event);" name="' + response[i].id + '">Update</button>')))
                    //.append($('<td>').append($('<button id="' +"updateDepartmentButton"+ response[i].id + '" class ="updateDepartmentButton"  name="' + response[i].id + '">Update</button>')))
                    .append($('<td>').append($('<button onclick="this.showAllEmployees(event);" name="' + response[i].id + '">Employees</button>')))
            );
            //$('.updateDepartmentButton').addEventListener("click",this.updateDepartment(event));
            //id="update_department'+ response[i].id+'"
            //$("#updateDepartmentButton" + id).addEventListener("click",this.updateDepartment(event));;
        }
        table.append($('<tr>')
            .append($('<td>')
                .append($('<button class="listen" onclick="this.addDepartment(event);">Add</button>')))
        );
        body.append(table);
    };

    displayDepartmentDetails(response) {
        let body = $('#page');
        body.text('');
        body.append(
            $('<form id="departmentForm" >')
                .append(
                    $('<table>')
                        .append($('<tr>')
                            .append($('<td>').text('Name:'))
                            .append($('<td>')
                                .append($('<input type="text" id="name" name ="name"/>').val(response !== null ? response.name : ""))//value="' + response.name + '"
                                .append($('<input type="hidden" id="id" value="' + response.id + '"/>'))
                            )
                        )
                        .append($('<tr>')
                            .append($('<td>')
                                .append($('<input type="submit" value="Save"/>'))
                            )
                        )
                )
        );
        this.validateDepartment();
    };

    validateDepartment() {
        $('#departmentForm').validate({
            rules: {
                name: {
                    required: true,
                    minlength: 5,
                    maxlength: 10,
                    remote: {
                        url: "/uniqueName",
                        type: "POST",
                        data: {
                            id: function () {
                                return $('#id').val();
                            },
                            name: function () {
                                return $('#name').val();
                            }
                        }
                    }
                }
            },
            messages: {
                name: {
                    required: "Type name, please",
                    minlength: "Your password must be at least 5 characters long",
                    maxlength: "Your password must not be longer than 10 characters",
                    remote: "This name is already used!"
                }
            },
            submitHandler: function () {
                this.saveDepartment();
            }
        });
    };

}
module.exports = DepartmentController;