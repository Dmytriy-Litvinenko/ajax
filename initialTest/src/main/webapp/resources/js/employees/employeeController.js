let showAllEmployees = function (event) {
    let departmentId = event.target.name;
    $.ajax({
        url: '/employees',
        type: 'GET',
        data: {departmentId: departmentId}/*,
        success: function (response) {
            displayEmployees(response, departmentId);
        }*/
    }).then((response) =>{
        displayEmployees(response, departmentId);
    });
};


let deleteEmployee = function (event) {
    let departmentId = $('#departmentId').val();
    $.ajax({
        url: '/empDelete',
        data: {employeeId: event.target.name},
        type: 'POST'/*,
        success: function (response) {
            displayEmployees(response, departmentId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }*/
    }).then((response) => {
        displayEmployees(response, departmentId);
        });
};

let updateEmployee = function (event) {
    $.ajax({
        url: '/employeeUpdate',
        data: {
            employeeId: event.target.name,
            departmentId: ""
        },
        type: 'POST'/*,
        success: function (response) {
            displayEmployeeDetails(response, response.department.id);
        }*/
    }).then((response)=>{
        displayEmployeeDetails(response, response.department.id);
    });
};
let addEmployee = function (event) {
    let departmentId = event.target.name;
    $.ajax({
        url: '/employeeUpdate',
        data: {
            employeeId: "",
            departmentId: departmentId
        },
        type: 'POST'/*,
        success: function (response) {
            displayEmployeeDetails(response, departmentId);
        }*/
    }).then((response)=>{
        displayEmployeeDetails(response, departmentId);
    });
};

let saveEmployee = function () {
    let empId = $('#id').val();
    let empName = $('#name').val();
    let empEmail = $('#email').val();
    let empSalary = $('#salary').val();
    let empBirthDate = $('#birthDate').val();
    let departmentId = $('#departmentId').val();
    let employee = {
        id: empId,
        name: empName,
        email: empEmail,
        salary: empSalary,
        birthDate: empBirthDate,
        department: {}
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(employee),
        dataType: "json",
        url: '/employeeSave?departmentId=' + departmentId,
        type: "POST"/*,
        success: function (data) {
            displayEmployees(data, departmentId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }*/

    }).then((data)=>{
        displayEmployees(data, departmentId);
    });
};
