'use strict';
import jQuery from "jquery";
import Service from "../service";
window.$ = window.jQuery = jQuery;

export default class EmployeeService extends Service {

    constructor() {
        super();
    }

    findAll(departmentId) {
        return super.findAll('/employees', departmentId);
    };

    _delete(id) {
        return super._delete('/empDelete', id);
    };

    update(id) {
        return super.update('/employeeUpdate', id);
    };

    add(id) {
        return super.add('/employeeUpdate', id);
    };

    save(employee, departmentId) {
        return super.save('/employeeSave', employee, departmentId);
    };
}
