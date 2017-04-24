var hi = function () {
    alert('hello, departments and employees');
};

//$(document).ready(function () {});

/*$(document).ready(function () {
 $('body').text('There will be departments here!!!');
 $.ajax({
 url:'/departments',
 type:'GET',
 success: function(response){
 data = response.data;
 $('body').text('There will be departments here!!!');
 }
 });
 });*/
/*$.ajax({
 url:'/departments',
 type:'GET',
 success: function(response){
 data = response.data;
 $('body').text('There will be departments here!!!');

 }
 });*/
var showAllDepartments = function () {
    $.ajax({
        url: '/departments',
        type: 'GET',
        success: function (response) {
            let body = $('body');
            body.text('');
            body.append('<table width="600px"> <tr> <td><b>Department Name</b></td> </tr>');
            for (let i = 0; i < response.length; i++) {
                body.append($('<tr>')
                    .append($('<td colspan="2">').text(response[i].name))
                    .append($('<td>')
                        .append($('<table>'))
                    .append('<button onclick="deleteDepartment('+response[i].id+');">Delete</button>')
                        /*.append('<form method="get" action="/delete/45">'
                        //+'<input type="hidden" name="id" value="'+response[i].id+'"/>'
                        +'<input type="submit" value="Delete"/>'
                        )*/
                    )
                    .append($('<td>')
                        .append('<form method="post" action="/depUpdate">'
                            + '<input type="hidden" name="departmentId" value="' + response[i].id + '"/>'
                            + '<input type="submit" value="Update"/>'
                        )
                    )
                    .append($('<td>')
                        .append('<form method="get" action="/employees">'
                            + '<input type="hidden" name="departmentId" value="' + response[i].id + '"/>'
                            + '<input type="submit" value="Employees"/>'
                        )
                    )
                );
            }
        }
    });
};

var deleteDepartment = function (event) {
    $.ajax({
        url: '/delete/',
        //data: {id: el.data('location')}
        data: ({ "id": event.target().value}),
        type: 'GET',
        success: function () {
            showAllDepartments();
        }
    });
};