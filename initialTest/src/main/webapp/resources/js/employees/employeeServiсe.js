//import saveEmployee from '../route/employees';


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
                            .append($('<input type="email" id="email" name="email"/>')
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

let validateEmployee = function () {
    $('#employeeForm').validate({
        rules: {
            name: {
                required: true,
                minlength: 5,
                maxlength: 10
            },
            email: {
                required: true,
                email: true,
                remote: {
                    url: "/uniqueEmail",
                    type: "POST",
                    data: {
                        id: function () {
                            return $('#id').val();
                        },
                        email: function () {
                            return $('#email').val();
                        }
                    }
                }
            },
            salary: {
                required: true,
                digits: true,
            },
            birthDate: {
                required: true,
                date: true
            }
        },
        messages: {
            name: {
                required: "Type name, please",
                minlength: "Your password must be at least 5 characters long",
                maxlength: "Your password must not be longer than 10 characters",
            },
            email: {
                required: "Type email, please",
                email: "Type correct email!!",
                remote: "This email is already used!"
            },
            salary: {
                required: "Input salary, please",
                digits: "Type only digits!",
            },
            birthDate: {
                required: "Type birthday, please",
                date: "input correct date"
            }
        },
        submitHandler: function () {
            saveEmployee();
        }
    });
};