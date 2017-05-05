'use strict';
import jQuery from "jquery";
//const User = require('./user.js');
const DepartmentController = require('./departments/depControllerOOP.js');
window.$ = window.jQuery = jQuery;
/*let user = new User();
 user.setName("Max");
 console.log(user.getName());*/
/*let depController = new DepartmentController();
 window.departmentController = depController;
 window.startShowing = function () {
 //window.departmentController =
 depController.showAllDepartments();
 };*/

class Main {
    static main(){
        let depController = new DepartmentController();
        depController.showAllDepartments();
    }
}
window.Main = Main;

/*
class Main {

    startShowing = function () {
        let page = document.getElementById("page");
        page.innerHTML = "<p>Hello JS SPA</p>" +
            "<button class='listen' value='DepAll'>All Department</button>";
    };

    static main() {
        let departmentController = new DepartmentController();

        let dispatcher = {
            departments: function () {
                departmentController.showAllDepartments();
            },
            DelDep: function () {
                departmentController.deleteDepartment(event);
            },
            AddDep: function () {
                departmentController.addDepartment();
            }
        };

        $('#page').on("click", ".listen", function (event) {
            let value = $(this).attr('value');
            dispatcher[value](event);
            //$(document).ready(function () {});
        });
    };
}*/


/*
 var contr = new Main();
 contr.showHeader();

 var service = new Service();

 var department = new Department();
 window.GlobDep = department;

 department.viewDepartmentsList();


 var Ingredients = {
 DepAll: function () {
 department.viewDepartmentsList();
 },
 DelDep: function () {
 department.DelDep(event)
 },
 AddDep: function () {
 department.viewDepartment();
 }

 };


 $('body').on("click", ".listen", function (event) {
 var value = $(this).attr('value');
 Ingredients[value](event);
 });

 */