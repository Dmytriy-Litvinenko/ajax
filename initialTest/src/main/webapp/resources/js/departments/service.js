'use strict';
import jQuery from "jquery";
//import "jquery-validation";
window.$ = window.jQuery = jQuery;

export default class DepartmentService {

    findAll() {
        let getDepartments = $.ajax({
            url: '/departments',
            type: 'GET'
        });
        return getDepartments;
    };

    delete(id) {
        let deleteDepartment = $.ajax({
            url: '/delete',
            data: {id: id},
            type: 'POST'
        });
        return deleteDepartment;
    };

    update(id) {
        let updateDepartment = $.ajax({
            url: '/update',
            data: {id: id},
            type: 'POST',
            dataType: 'json'
        });
        return updateDepartment;
    };

    add(departmentId) {
        let addDepartment = $.ajax({
            url: '/update',
            data: {
                departmentId: departmentId
            },
            type: 'POST'
        });
        return addDepartment;
    };

    save(id, name) {
        let saveDepartment = $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                id: id,
                name: name
            }),
            url: '/saveDep',
            type: "POST"
        });
        return saveDepartment;
    };
}
