<#import "parts/common.ftl" as c>

<@c.page>
<h1>Tags list: </h1>
<p>${tag_resp.tag.id}
<p>${tag_resp.tag.title}

<#list tag_resp.tasks as task>
<div> 
    <div style="display: inline-block">${task.name}</div>
    <div style="display: inline-block">${task.description}</div>
    <div style="display: inline-block">${task.task_date}</div>
    <div style="display: inline-block">${task.taguid}</div>
</div>
</#list>
</@c.page>