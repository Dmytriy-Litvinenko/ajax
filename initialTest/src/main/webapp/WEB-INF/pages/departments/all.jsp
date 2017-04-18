<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html class="no-js">
<head>
    <title>All</title>
</head>
<table width="600px">
    <tr>
        <td><b>Department Name</b></td>
    </tr>
    <c:forEach items="${departments}" var="department">
        <tr>
            <td>${department.name}</td>
            <td>
                <form method="get" action="/depDelete">
                    <table>
                        <input type="hidden" name="departmentId" value="${department.id}"/>
                        <input type="submit" value="Delete"/>
                    </table>
                </form>
            </td>
            <td>
                <form method="post" action="/depUpdate">
                    <table>
                        <input type="hidden" name="departmentId" value="${department.id}"/>
                        <input type="submit" value="Update"/>
                    </table>
                </form>
            </td>
            <td>
                <form method="get" action="/employees">
                    <table>
                        <input type="hidden" name="departmentId" value="${department.id}"/>
                        <input type="submit" value="Employees"/>
                    </table>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <form method="post" action="/depUpdate">
                <table>
                    <input type="submit" value="Add"/>
                </table>
            </form>
        </td>
    </tr>
</table>