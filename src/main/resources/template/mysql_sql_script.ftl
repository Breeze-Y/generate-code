create table ${tableName}
(
    <#list fieldList as field>
        ${field.column} ${field.length} null comment '${field.comment}' <#if field.id>primary key</#if><#if field_has_next>,</#if>
    </#list>
);