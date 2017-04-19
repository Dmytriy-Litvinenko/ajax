<%--@ page contentType="text/html;charset=UTF-8" language="java" --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Department data</title>
    <style>
        .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form method="post" action="/depSave" ><%-- departmentName   "--%>
    <table>
        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" value="${department.name}"/>
                <input type="hidden" name="id" value="${department.id}"/>
            </td>
            <td class="error">${errors.get("name")}</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="id" />
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
