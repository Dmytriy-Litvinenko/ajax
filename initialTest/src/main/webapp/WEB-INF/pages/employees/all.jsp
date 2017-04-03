<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="no-js">
<head>
    <title>All Employees</title>
</head>
<table width="600px">
    <tr>
        <td><b>Name</b></td>
        <td><b>Email</b></td>
        <td><b>Salary</b></td>
        <td><b>Birth date</b></td>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td>${employee.salary}</td>
            <td>${employee.birthDate}</td>
            <td>
                <form method="get" action="/empDelete">
                    <table>
                        <input type="hidden" name="employeeId" value="${employee.id}"/>
                        <input type="submit" value="Delete"/>
                    </table>
                </form>
            </td>
            <td>
                <form method="post" action="/employeeUpdate">
                    <table>
                        <input type="hidden" name="employeeId" value="${employee.id}"/>
                        <input type="submit" value="Update"/>
                    </table>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <form method="post" action="/employeeUpdate">
                <table>
                    <input type="hidden" name="departmentId" value="${departmentId}"/>
                    <input type="submit" value="Add"/>
                </table>
            </form>
        </td>
    </tr>
</table>