'use strict';
import jQuery from "jquery";
import "jquery-validation";
window.$ = window.jQuery = jQuery;

export default class DepartmentView {

    displayDepartments = (response) => {
        let body = $('#page');
        body.text('');
        let table = $('<table>');
        table.append($('<tr>').append($('<td>').append($('<b>').text('Department Name'))));
        for (let i = 0; i < response.length; i++) {
            table.append(
                $('<tr>')
                    .append($('<td>').text(response[i].name))
                    .append($('<td>').append($('<button class="listener" value="deleteDepartment" name="' + response[i].id + '">Delete</button>')))
                    .append($('<td>').append($('<button class="listener" value="updateDepartment" name="' + response[i].id + '" >Update</button>')))
                    .append($('<td>').append($('<button class="listener" value="showAllEmployees" name="' + response[i].id + '">Employees</button>')))
            );
        }
        table.append($('<tr>')
            .append($('<td>')
                .append($('<button class="listener" value="addDepartment" >Add</button>')))
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
                                .append($('<button type="submit" id="saveDepartment" value="saveDepartment">Save</button>'))
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
                            id: () => {
                                return $('#id').val();
                            },
                            name: () => {
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
            submitHandler: () => {
                $( "#saveDepartment" ).addClass('listener').trigger( 'click' );
            }
        });
    };

}