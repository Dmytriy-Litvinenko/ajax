'use strict';
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;

export default class DepartmentService {

    findAll(departmentId) {
        let getAllEmployees =
            $.ajax({
                url: '/employees',
                type: 'GET',
                data: {departmentId: departmentId}
            });
        return getAllEmployees;
    };

    delete(id) {
        let deleteEmployee =
            $.ajax({
                url: '/empDelete',
                data: {employeeId: id},
                type: 'POST'
            });
        return deleteEmployee;
    };

    update(id) {
        let updateEmployee =
            $.ajax({
                url: '/employeeUpdate',
                data: {
                    employeeId: id,
                    departmentId: ""
                },
                type: 'POST'
            });
        return updateEmployee;
    };

    add(id) {
        let addEmployee =
            $.ajax({
                url: '/employeeUpdate',
                data: {
                    employeeId: "",
                    departmentId: id
                },
                type: 'POST'
            });
        return addEmployee;
    };

    save(employee, departmentId) {
        let saveEmployee =
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(employee),
                dataType: "json",
                url: '/employeeSave?departmentId=' + departmentId,
                type: "POST"

            });
        return saveEmployee;
    };
}
