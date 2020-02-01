<#import "parts/common.ftl" as c>

<@c.page>
<h1>Tags list: </h1>
<#list tags as tag>
    <p>${tag.title}
<#else>
    <p> Tags list empty
</#list>
</@c.page>