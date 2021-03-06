<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employee Data</title>
    <style>
        .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
</head>
<body>
<form method="post" action="/employeeSave">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${employee.name}"/></td>
            <td class="error">${errors.get("name")}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${employee.email}"/></td>
            <td class="error">${errors.get("email")}</td>
        </tr>
        <tr>
            <td>Salary:</td>
            <td><input type="text" name="salary" value="${param['employeeSalary'] == null ? employee.salary : param['employeeSalary']}" /></td>
            <td class="error">${errors.get("salary")}</td>
        </tr>
        <tr>
            <td>Birth Date:</td>
            <fmt:formatDate pattern="yyyy-MM-dd" value="${employee.birthDate}" var="birth"/>
            <td><input type="date" value= "${birth}" name="birthDate"/></td>
            <td class="error">${errors.get("birthDate")}</td>

        </tr>
        <tr>
            <td>
            <td><input type="hidden" name="departmentId" value="${departmentId}"/></td>
            <td><input type="hidden" name="id" value="${employee.id}"/></td>
            <td> <input type="submit" value="Save"/></td>
        </tr>
    </table>
</form>
</body>
</html>