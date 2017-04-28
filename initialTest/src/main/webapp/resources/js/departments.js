let hi = function () {
    alert('hello, departments and employees');
};

let showAllDepartments = function () {
    $.ajax({
        url: '/departments',
        type: 'GET',
        success: function (response) {
            displayDepartments(response);
        }
    });
};

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

let deleteDepartment = function (event) {
    $.ajax({
        url: '/delete',// + id,
        data: {id: event.target.name},
        type: 'POST',
        success: function (response) {
            displayDepartments(response);
        }
    });
};

let updateDepartment = function (event) {
    $.ajax({
        url: '/update',
        data: {id: event.target.name},
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            displayDepartmentDetails(response);
        }
    });
};

let addDepartment = function (event) {
    let departmentId = event.target.name;
    $.ajax({
        url: '/update',
        data: {
            departmentId: departmentId
        },
        type: 'POST',
        success: function (response) {
            displayDepartmentDetails(response);
        }
    });
};

let displayDepartmentDetails = function (response) {
    let body = $('body');
    body.text('');
    body.append(
        $('<form id="departmentForm" >')//method="post" action="/saveDep"
            .append(
                $('<table>')
                    .append($('<tr>')
                        .append($('<td>').text('Name:'))
                        .append($('<td>')
                            .append($('<input type="text" id="name" name ="name" minlength="5"/>').val(response !== null ? response.name : ""))//value="' + response.name + '"
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

let saveDepartment = function () {
    //$('#departmentForm').submit(function () {return false;});
    let depId = $('#id').val();
    let depName = $('#name').val();
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify({
            id: depId,
            name: depName
        }),
        url: '/saveDep',
        type: "POST",
        success: function () {//data, textStatus, jqXHR
            showAllDepartments();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
};

let validateDepartment = function() {
    $('#departmentForm').validate({
        rules: {
            name: {
                //required: true,
                minlength: 5,
                maxlength: 10,
                remote: {
                    /*headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },*/
                    /*data: JSON.stringify({
                        id: depId,
                        name: depName
                    }),*/
                    url: "/uniqueName",
                    type: "POST",
                    //datatype:json,
                    data: //JSON.stringify({
                        {
                        id:function(){return $('#id').val();},
                        name:function(){return $('#name').val();}
                    }//)//id: function() {return ;}
                }
            }
        },
        messages: {
            name: {
                //required: "Please provide a password"/*,
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