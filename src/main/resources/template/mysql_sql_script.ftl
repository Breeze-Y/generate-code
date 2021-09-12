CREATE TABLE ${tableName}
(
    <#list fieldList as field>
        ${field.column} ${field.length} NULL COMMENT '${field.comment}' <#if field.id>PRIMARY KEY</#if><#if field_has_next>,</#if>
    </#list>
) comment '${tableComment}';