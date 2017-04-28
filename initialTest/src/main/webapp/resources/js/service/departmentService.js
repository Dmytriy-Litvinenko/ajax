let displayDepartments = function (response) {
    let body = $('body');
    body.text('');
    let table = $('<table>');
    table.append($('<tr>').append($('<td>').append($('<b>').text('Department Name'))));
    for (let i = 0; i < response.length; i++) {
        table.append(
            $('<tr>')
                .append($('<td>').text(response[i].name))
                .append($('<td>').append($('<button onclick="deleteDepartment(event);" name="' + response[i].id + '">Delete</button>')))
                .append($('<td>').append($('<button onclick="updateDepartment(event);" name="' + response[i].id + '">Update</button>')))
                .append($('<td>').append($('<button onclick="showAllEmployees(event);" name="' + response[i].id + '">Employees</button>')))
        );
    }
    table.append($('<tr>')
        .append($('<td>')
            .append($('<button onclick="addDepartment(event);">Add</button>')))
    );
    body.append(table);
};

let displayDepartmentDetails = function (response) {
    let body = $('body');
    body.text('');
    body.append(
        $('<form id="departmentForm" >')
            .append(
                $('<table>')
                    .append($('<tr>')
                        .append($('<td>').text('Name:'))
                        .append($('<td>')
                            .append($('<input type="text" id="name" name ="name"/>').val(response !== null ? response.name : ""))//value="' + response.name + '"
                            .append($('<input type="hidden" id="id" value="' + response.id + '"/>'))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>')
                            .append($('<input type="submit" value="Save"/>'))
                        )
                    )
            )
    );
    validateDepartment();
};

let validateDepartment = function () {
    $('#departmentForm').validate({
        rules: {
            name: {
                required: true,
                minlength: 5,
                maxlength: 10,
                remote: {
                    url: "/uniqueName",
                    type: "POST",
                    data: {
                        id: function () {
                            return $('#id').val();
                        },
                        name: function () {
                            return $('#name').val();
                        }
                    }
                }
            }
        },
        messages: {
            name: {
                required: "Type name, please",
                minlength: "Your password must be at least 5 characters long",
                maxlength: "Your password must not be longer than 10 characters",
                remote: "This name is already used!"
            }
        },
        submitHandler: function () {
            saveDepartment();
        }
    });
};