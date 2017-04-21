<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!--meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->
    <title></title>
    <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"/-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

</head>
    <body style="text-align:center">
        <h2>Hello, click the following button to see the departments list!</h2>
        <!--<form method="get" action="/departments">
            <table>
                <input type="submit" value="Departments"/>
            </table>
            alert("hello, departments")
        </form>hello();hi();-->
        <button onclick='hi();'>Departments</button>
        <br/>
        <div>
            <img src="/resources/images/wellcome.jpg" width="100%"/>
        </div>
    </body>
<script>
    var hi = function () {
        $.ajax({
            url:'/departments',
            type:'GET',
            success: function(response){
                //var  = response.;
                $('body').text("There will be departments here!!!"+response.length);
                $('body').append("<table width='600px'> <tr> <td><b>Department Name</b></td> </tr>");
                for(var i=0; i<response.length; i++){
                    $("body").append("<tr class='tr'> <td> "+response[i].name+" </td>");
                }
                $('body').append("</tr> </table>");
            }
        });
    };
    /*+data.lengthvar hello = function () {
     alert('hello, departments and employees');
     };*/
</script>

<!--script src="/resources/js/jquery-3.2.1.min.js"/-->
<!--script src="/resources/js/departments.js"/-->
<%--script src='<c:url value="/resources/js/departments.js" />'/--%>
</html>