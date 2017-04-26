/**
 * Created by user on 26.04.17.
 */

let showAllEmployees = function (event) {
    $.ajax({
        url: '/employees',
        type: 'GET',
        data: {departmentId: event.target.name},
        success: function (response) {
            displayEmployees(response);
        }
    });
};

let displayEmployees = function (response) {
    let body = $('body');
    body.text('');
    let table = $('<table>');
    table.append($('<tr>')
        .append($('<td>').append($('<b>').text('Name')))
        .append($('<td>').append($('<b>').text('Email')))
        .append($('<td>').append($('<b>').text('Salary')))
        .append($('<td>').append($('<b>').text('Birth Date')))
    );
    for (let i = 0; i < response.length; i++) {
        table.append($('<tr>')
            .append($('<td>').text(response[i].name))
            .append($('<td>').text(response[i].email))
            .append($('<td>').text(response[i].salary))
            .append($('<td>').text(response[i].birthDate))
            .append($('<td>').append($('<button onclick="deleteEmployee(event);" name="' + response[i].id + '">Delete</button>')))
            .append($('<td>').append($('<button onclick="updateEmployee(event);" name="' + response[i].id + '">Update</button>')))
        );
    }
    body.append(table);
};

let deleteEmployee = function (event) {
    $.ajax({
        url: '/empDelete',
        data: {employeeId: event.target.name},
        type: 'POST',
        success: function (response) {
            displayEmployees(response);
        }
    });
};

let updateEmployee = function (event) {
    //alert('updating');
    $.ajax({
        url: '/employeeUpdate',
        data: {
            employeeId: event.target.name,
            departmentId: ""
        },
        type: 'POST',
        success: function (response) {
            displayEmployeeDetails(response);
        }
    });
};

var displayEmployeeDetails = function (response) {
    let body = $('body');
    body.text('');
    body.append(
        $('<form id="employeeForm" method="post" action="/employeeSave">')
            .append(
                $('<table>')
                    .append($('<tr>')
                        .append($('<td>').text('Name:'))
                        .append($('<td>')
                            .append($('<input type="text" id="name" value="' + response.name + '"/>'))
                            .append($('<input type="hidden" id="id" value="' + response.id + '"/>'))
                            .append($('<input type="hidden" id="departmentId" value="' + response.department.id + '"/>'))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Email:'))
                        .append($('<td>')
                            .append($('<input type="text" id="email" value="' + response.email + '"/>'))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Salary:'))
                        .append($('<td>')
                            .append($('<input type="text" id="salary" value="' + response.salary + '"/>'))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Birth Date:'))
                        .append($('<td>')
                            .append($('<input type="text" id="birthDate" value="' + response.birthDate + '"/>'))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>')
                            .append($('<button type="submit" onclick="saveEmployee();" value="Save">Save</button>'))
                        )
                    )
            )
    )
};

let saveEmployee = function () {
    //event.preventDefault();
    let form =$('#employeeForm');
    form.submit(function () {
        return false;
    });
    let empId = $('#id').val();
    let empName = $('#name').val();
    let empEmail = $('#email').val();
    let empSalary = $('#salary').val();
    let empBirthDate = $('#birthDate').val();
    let departmentId = $('#departmentId').val();
    //var str = form.serialize();
    let employee={
        id: empId,
        name: empName,
        email: empEmail,
        salary: empSalary,
        birthDate: empBirthDate,
        department: {}
    };
    //alert('id = '+depId+'; name = '+depName);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        /*data: JSON.stringify({
            employee:{
                id: empId,
                name: empName,
                email: empEmail,
                salary: empSalary,
                birthDate: empBirthDate,
                department: {}
            },
            departmentId: departmentId
        }),*/
        data:{
            employee:JSON.stringify(employee),
            departmentId: departmentId
        },
        dataType: "json",
        url: '/employeeSave',
        type: "POST",
        success: function (data, textStatus, jqXHR) {
            //alert('success'+data.name);
            showAllEmployees();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
};