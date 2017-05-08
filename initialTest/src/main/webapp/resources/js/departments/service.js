'use strict';
import jQuery from "jquery";
import Service from "../service";
window.$ = window.jQuery = jQuery;

export default class DepartmentService extends Service {

    constructor() {
        super();
    }

    findAll() {
        return super.findAll('/departments', null);
    };

    _delete(id) {
        return super._delete('/delete', id);
    };

    update(id) {
        return super.update('/update', id);
    };

    add(departmentId) {
        return super.add('/update', departmentId);
    };

    save(department, id) {
        return super.save('/saveDep', department, id);
    };
}
