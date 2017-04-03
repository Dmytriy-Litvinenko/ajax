<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" action="/depSave">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="departmentName" value="${department.name}"/></td>
            <td><input type="hidden" name="departmentId" value="${department.id}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
