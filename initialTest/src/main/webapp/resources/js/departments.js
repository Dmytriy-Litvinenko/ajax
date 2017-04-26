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
    body.append('<table> <tr> <td><b>Department Name</b></td> </tr>');
    for (let i = 0; i < response.length; i++) {
        body.append($('<tr>')
            .append($('<td>').text(response[i].name))
            .append($('<td>').append($('<button onclick="deleteDepartment(event);" name="' + response[i].id + '">Delete</button>')))
            .append($('<td>').append($('<button onclick="updateDepartment(event);" name="' + response[i].id + '">Update</button>')))
            .append($('<td>').append($('<button onclick="showAllEmployees(event);" name="' + response[i].id + '">Employees</button>')))
        );
    }
};

let deleteDepartment = function (event) {
    //alert('deleting'+event.target.name);
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
    //alert('updating' + event.target.name);
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

let displayDepartmentDetails = function (response) {
    let body = $('body');
    body.text('');
    body.append(
        $('<form id="departmentForm" method="post" action="/saveDep">')//
            .append(
                $('<table>')
                    .append($('<tr>')
                        .append($('<td>').text('Name:'))
                        .append($('<td>')
                            .append($('<input type="text" id="name" value="' + response.name + '"/>'))
                            .append($('<input type="hidden" id="id" value="' + response.id + '"/>'))
                        )
                    )
                    .append($('<tr>')
                        .append($('<td>')//type="button"
                            .append($('<button type="submit" onclick="saveDepartment();" value="Save">Save</button>'))
                        )
                    )
            )
    )
};

let saveDepartment=function() {
    //event.preventDefault();
    $('#departmentForm').submit(function(){return false;});
    let depId =$('#id').val();
    let depName =$('#name').val();/**/
    //alert('id = '+depId+'; name = '+depName);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data : JSON.stringify({
            id : depId,
            name : depName
        }),
        url : '/saveDep',
        type: "POST",
        success:function(data, textStatus, jqXHR) {
            //alert('success'+data.name);
            showAllDepartments();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
};