<#import "parts/common.ftl" as c>

<@c.page>
<h1>Task list: </h1>
<table>
<#list tasks as task>
    <tr>
    <td>${task.name}</td>
    <td>${task.description}</td>
    <td>${task.task_date}</td>
    <td>${task.taguid}</td>
    </tr>
<#else>
    <p> Task list empty
</#list>
</table>
</@c.page>