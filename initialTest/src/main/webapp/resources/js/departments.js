var hi = function () {
    alert('hello, departments and employees');
};

let displayDepartments = function (response) {
    let body = $('body');
    body.text('');
    body.append('<table width="600px"> <tr> <td><b>Department Name</b></td> </tr>');
    for (let i = 0; i < response.length; i++) {
        body.append($('<tr>')
            .append($('<td colspan="2">').text(response[i].name))
            .append($('<td>')
                .append($('<button onclick="deleteDepartment(event);" name="' + response[i].id + '">Delete</button>'))
            )
            .append($('<td>')
                .append($('<button onclick="updateDepartment(event);" name="' + response[i].id + '">Update</button>'))
            )
            .append($('<td>')
                .append('<form method="get" action="/employees">'
                    + '<input type="hidden" name="departmentId" value="' + response[i].id + '"/>'
                    + '<input type="submit" value="Employees"/>'
                )
            )
        );
    }
};

var showAllDepartments = function () {
    $.ajax({
        url: '/departments',
        type: 'GET',
        success: function (response) {
            displayDepartments(response);
        }
    });
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
            displayDepartmentDetails1(response);
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
                            .append($('<button type="submit" onclick="saveDepartment(event,this);" value="Save">Save</button>'))
                        )
                    )
            )
    )
};

let displayDepartmentDetails1 = function (response) {
    let body = $('body');
    body.text('');
    body.append('<form id="departmentForm" action="/saveDep" method="POST">' +
        'name: <input type="text" id="name" name="name" value ="'+response.name+'"/> <br/>'+
        '<input type="hidden" id="id" name="id" value ="'+response.id +'" />' +
        ' <br/><button type="submit" onclick="save(event);">Save</button></form>');//<input type="submit" value="Save">
};
/*
<form name="ajaxform" id="ajaxform" action="ajax-form-submit.php" method="POST">
    First Name: <input type="text" name="fname" value =""/> <br/>
    Last Name: <input type="text" name="lname" value ="" /> <br/>
    Email : <input type="text" name="email" value=""/> <br/>
    </form>
*/
/*$("#departmentForm").submit(function(e) {

    // the script where you handle the form input.

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "/saveDep",
        data: JSON.stringify($("#departmentForm").serialize()), // serializes the form's elements.
        success: function(data)
        {
            alert(data); // show response from the php script.
        }
    });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});*/

 let save=function(event) {
     //event.preventDefault();
     $('#departmentForm').submit(function(){return false;});
     let depId =$('#id').val();
     let depName =$('#name').val();/**/
     //alert('id = '+depId+'; name = '+depName);
     $.ajax(
     {
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

 //$("#ajaxform").submit(); //Submit  the FORM
 /**/

let saveDepartment = function(event, form) {
    alert('saving'+$(form).find('#id').text);
//.attr("action"
    //let url = form.attr("action");
    //var formData = $(form).serializeArray();

    alert('saving' + url);
    $.ajax({
        url: '/update',
        data: {id: event.target.name},
        type: 'POST',
        dataType: 'json',
        success: function (response) {
           // displayDepartmentDetails(response);
        }
    });
    /*function submitForm(form){
     var url = form.attr("action");
     var formData = $(form).serializeArray();
     $.post(url, formData).done(function (data) {
     alert(data);
     });
     }*/

    //form, fname, lname) {
    /*action = $(form).attr("action");
     $.post(att, $(form).serialize() ).done(function (data) {
     alert(data);
     });
     return true;*/
}
/*var dep = $(value).data("dep");

 var action = event.target.id;
 if ("btn_del" === action) {
 deleteDep(dep);
 } else if ("btn_upd" === action) {
 addNewDepartments(dep);
 } else if ("bnt_list_empl") {
 showListEmpl(dep);
 }*/

/*
Department.prototype.saveDepartment = function (id) {
    var name = document.getElementById("name").value;
    var depart = {"id": id, "name": name};
    console.log("department");
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "/depSaveOrUpdate",
        data: JSON.stringify(depart),
        success: function (data) {
            //
            if (data.status == "SUCCESS") {
                service.BuildDep(data.department);
            } else {
                service.viewDepartment(depart, data.error.name)
            }
        }
    });
};

Department.prototype.EditOrSave = function (id) {
    $.ajax({
        url: '/editOrAddDep',
        data: ({
            id: id
        }),
        dataType: 'json',
        type: 'POST',
        success: function (data) {
            service.viewDepartment(data)

        }
    });

};*/