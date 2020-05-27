<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>

<table>
    <#list students as item >
        <tr>
            <td>${item.name}</td>
            <td>${item.age}</td>
            <td>${item.address}</td>
            <td>${(item.birth)!?string("yyyy-MM-dd HH:mm:ss")}</td>
        </tr>
    </#list>


</table>


</body>