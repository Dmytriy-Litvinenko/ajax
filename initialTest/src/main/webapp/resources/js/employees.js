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
        let birthDate = new Date(response[i].birthDate).toLocaleString('en-GB').split(' ')[0].slice(0, -1).split('/').reverse().join('-');
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
    let birthDate = new Date(response.birthDate).toLocaleString('en-GB').split(' ')[0].slice(0, -1).split('/').reverse().join('-');
    body.text('');
    body.append(
        $('<form id="employeeForm" method="post" action="/employeeSave">')
            .append(
                $('<table>')
                    .append($('<tr>')
                        .append($('<td>').text('Name:'))
                        .append($('<td>')
                            .append($('<input type="text" id="name" name="name"/>')
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
                            .append($('<input type="text" id="email" name="email"/>')
                                .val(response !== null ? response.email : ""))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Salary:'))
                        .append($('<td>')
                            .append($('<input type="text" id="salary" name="salary"/>')
                                .val(response !== null ? response.salary : ""))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>').text('Birth Date:'))
                        .append($('<td>')
                            .append($('<input type="date" id="birthDate" name="birthDate"/>')
                                .val(response !== null ? birthDate : ""))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>')
                            .append($('<input type="submit" value="Save"/>'))//onclick="saveEmployee();Save</button>"
                        )
                    )
            )
    );
    validateEmployee();
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

let validateEmployee = function () {
    $('#employeeForm').validate({
        rules: {
            name: {
                required: true,
                minlength: 5,
                maxlength: 10,
                remote: {
                    url: "/uniqueEmail",
                    type: "POST",
                    data: {
                        id: function () {
                            return $('#id').val();
                        },
                        name: function () {
                            return $('#email').val();
                        }
                    }
                },
                email: {
                    email: true
                },
                birthDate: {
                    required: true,
                    date: true
                },
                salary: {
                    required: true,
                    digits: true,
                }
            }
        },
        messages: {
            name: {
                required: "type name, please",
                minlength: "Your password must be at least 5 characters long",
                maxlength: "Your password must not be longer than 10 characters",
                remote: "This name is already used!"
            },
            email: {
                email: "Type correct email!!",
                remote: "This email is already used!"
            },
            birthDate: {
                required: "Type birthday, please",
                date: "input correct date"
            },
            salary: {
                required: "Type birthday, please",
                digits: "Type only digits!",
            }
        },
        submitHandler: function () {
            saveEmployee();
        }
    });
};

/*
 {
 rules: {
 input_first: {
 required: true,
 minlength: 3,
 maxlength: 20
 },
 input_second: {
 required: true,
 minlength: 3,
 maxlength: 20
 },
 input_email: {
 required: true,
 email: true
 },
 input_birthday: {
 required: true,
 date: true
 }

 }, messages: {
 input_first: {
 minlength: "Min length is 3",
 maxlength: "Min length is 20",
 required: "This is required field"
 },
 input_second: {
 minlength: "Min length is 3",
 maxlength: "Min length is 20",
 required: "This is required field"
 },
 input_email: {
 required: "This is required field",
 email: "Not valid email"
 },
 input_birthday: {
 required: "This is required field"
 }
 }*/