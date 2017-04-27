let showAllEmployees = function (event) {
    let departmentId = event.target.name;
    $.ajax({
        url: '/employees',
        type: 'GET',
        data: {departmentId: departmentId},
        success: function (response) {
            displayEmployees(response, departmentId);
        }
    });
};

let displayEmployees = function (response, departmentId) {
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
        let birthDate = new Date(response[i].birthDate).toLocaleString('en-GB').split(' ')[0].slice(0,-1).split('/').reverse().join('-');
        table.append($('<tr>')
            .append($('<p id="departmentId" />').val(departmentId).hide())
            .append($('<td>').text(response[i].name))
            .append($('<td>').text(response[i].email))
            .append($('<td>').text(response[i].salary))
            .append($('<td>').text(birthDate))
            .append($('<td>').append($('<button  onclick="deleteEmployee(event);" name="' + response[i].id + '">Delete</button>')))
            .append($('<td>').append($('<button onclick="updateEmployee(event);" name="' + response[i].id + '">Update</button>')))
        );
    }
    table.append($('<tr>')
        .append($('<td>')
            .append($('<button onclick="addEmployee(event);" name="' + departmentId + '">Add</button>')))
    );
    body.append(table);
};

let deleteEmployee = function (event) {
    let departmentId = $('#departmentId').val();
    $.ajax({
        url: '/empDelete',
        data: {employeeId: event.target.name},
        type: 'POST',
        success: function (response) {
            displayEmployees(response, departmentId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
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
            displayEmployeeDetails(response, response.department.id);
        }
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
        type: 'POST',
        success: function (response) {
            displayEmployeeDetails(response, departmentId);
        }
    });
};

let displayEmployeeDetails = function (response, departmentId) {
    let body = $('body');
    let birthDate = new Date(response.birthDate).toLocaleString('en-GB').split(' ')[0].slice(0,-1).split('/').reverse().join('-');
    body.text('');
    body.append(
        $('<form id="employeeForm" method="post" action="/employeeSave">')
            .append(
                $('<table>')
                    .append($('<tr>')
                        .append($('<td>').text('Name:'))
                        .append($('<td>')
                            .append($('<input type="text" id="name" />')
                                .val(response !== null ? response.name : ""))
                            .append($('<input type="hidden" id="id" />')
                                .val(response !== null ? response.id : ""))
                            .append($('<input type="hidden" id="departmentId"/>')
                                .val(departmentId))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Email:'))
                        .append($('<td>')
                            .append($('<input type="text" id="email"/>')
                                .val(response !== null ? response.email : ""))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Salary:'))
                        .append($('<td>')
                            .append($('<input type="text" id="salary"/>')
                                .val(response !== null ? response.salary : ""))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Birth Date:'))
                        .append($('<td>')
                            .append($('<input type="date" id="birthDate"/>')
                                .val(response !== null ? birthDate : ""))
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
    let form = $('#employeeForm');
    form.submit(function () {
        return false;
    });
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
        type: "POST",
        success: function (data) {
            displayEmployees(data, departmentId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
};