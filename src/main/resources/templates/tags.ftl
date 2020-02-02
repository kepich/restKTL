<#import "parts/common.ftl" as c>

<@c.page>
<h1>Tags list: </h1>
<table>
<#list tags as tag>
    <tr>
    <td><p>${tag.id}</td>
    <td><p>${tag.title}</td>
    </tr>
<#else>
    <p> Tags list empty
</#list>
</table>
</@c.page>