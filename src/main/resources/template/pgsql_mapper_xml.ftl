<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperFullyQualifiedName}">
    <resultMap id="BaseResultMap" type="${classFullyQualifiedName}">
        <#list fieldList as field>
            <#if field.id>
                <id column="${field.column}" jdbcType="${field.jdbcType}" property="${field.name}"/>
            <#else>
                <result column="${field.column}" jdbcType="${field.jdbcType}" property="${field.name}"/>
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list fieldList as field>${field.column}<#if field_has_next>,</#if></#list>
    </sql>

</mapper>