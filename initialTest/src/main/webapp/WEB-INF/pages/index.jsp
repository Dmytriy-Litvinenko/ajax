<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
    <body>
        <div id="page" style="text-align:center">
            <h2>Hello, click the following button to see the departments list!</h2>
            <!--button onclick='showAllDepartments();'>Departments</button-->
            <!--button onclick='startShowing()'>Departments</button-->
            <button onclick='Main.main();'>Departments</button>

            <br/>
            <div id="logs">
                <img src="/resources/images/wellcome.jpg" width="100%"/>
            </div>
        </div>
        <script src="/resources/js/bundle.js"></script>
    </body>
</html>