<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department data</title>
    <style>
        .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
</head>
<body>
<form method="post" action="/depSave">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="departmentName" value="${department.name}"/></td>
            <td class="error">${errors.get("name")}</td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="departmentId" value="${department.id}"/>
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
