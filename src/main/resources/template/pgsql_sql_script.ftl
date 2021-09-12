CREATE TABLE ${tableName}
(
    <#list fieldList as field>
        ${field.column} ${field.length} null <#if field.id>PRIMARY KEY</#if><#if field_has_next>,</#if>
    </#list>
);

COMMENT ON TABLE ${tableName} IS '${tableComment}';

<#list fieldList as field>
COMMENT ON COLUMN ${tableName}.${field.column} IS '${field.comment}';
<#if field_has_next> </#if>
</#list>
