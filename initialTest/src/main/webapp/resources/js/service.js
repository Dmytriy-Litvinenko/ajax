'use strict';
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;

export default class Service {

    findAll(url, id) {
        let getEntities =
            $.ajax({
                url: url,
                type: 'GET',
                data: id === null ? null : {departmentId: id}
            });
        return getEntities;
    };

    _delete(url, id) {
        let deleteEntity =
            $.ajax({
                url: url,
                data: {id: id},
                type: 'POST'
            });
        return deleteEntity;
    };


    update(url, id) {
        let updateEntity =
            $.ajax({
                url: url,
                data: url === '/update' ? {id: id} : {employeeId: id, departmentId: ""},
                type: 'POST',
                dataType: 'json'
            });
        return updateEntity;
    };

    add(url, id) {
        let addEntity =
            $.ajax({
                url: url,
                data: url === '/update' ? {departmentId: id} : {employeeId: "", departmentId: id},
                type: 'POST'
            });
        return addEntity;
    };

    save(url, entity, departmentId) {
        let saveEntity =
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(entity),
                dataType: "json",
                url: url === '/saveDep' ? '/saveDep' : '/employeeSave?departmentId=' + departmentId,
                type: "POST"

            });
        return saveEntity;
    };
}
