<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!--meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->
    <title></title>
</head>
    <body>
        <div style="text-align:center">
            <h2>Hello, click the following button to see the departments list!</h2>
            <button onclick='showAllDepartments();'>Departments</button>
            <button onclick='goToIndexJs();'>Departments in OOP</button>
            <br/>
            <div>
                <img src="/resources/images/wellcome.jpg" width="100%"/>
            </div>
        </div>
    </body>
<script type='text/javascript' src="/resources/js/jquery-3.2.1.min.js"></script>
<script type='text/javascript' src="/resources/js/jquery.validation.min.js"></script>
<script type='text/javascript' src='/resources/js/bundle.js'></script>
<script type='text/javascript' src='/resources/js/depControllerOOP.js'></script>
<script type='text/javascript' src='/resources/js/departments/depServiceOOP.js'></script>
<!--script type='text/javascript' src='/resources/js/departments/departmentService.js'></script>
<script type='text/javascript' src='/resources/js/departments/departmentController.js'></script>
<script type='text/javascript' src='/resources/js/employees/employeeServiсe.js'></script>

<script type='text/javascript' src='/resources/js/employees/employeeController.js'></script-->
</html>