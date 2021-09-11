package top.breezes.model;

import com.google.common.base.CaseFormat;
import com.thoughtworks.qdox.model.JavaField;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/11 13:15
 * @description
 */
public class Field {

    private Boolean id;

    private String type;

    private String name;

    private String column;

    private String jdbcType;

    public static Field create(JavaField field) {
        Field newField = new Field();
        newField.setId("id".equals(field.getName()) || "uuid".equals(field.getName()));
        newField.setName(field.getName());
        newField.setColumn(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()));
        newField.setJdbcType(javaType2JdbcMap.getOrDefault(field.getType().getName(), javaType2JdbcMap.get("String")));
        return newField;
    }

    static Map<String, String> javaType2JdbcMap = new HashMap<>();

    static {
        javaType2JdbcMap.put("int", "INTEGER");
        javaType2JdbcMap.put("long", "BIGINT");
        javaType2JdbcMap.put("double", "DECIMAL");
        javaType2JdbcMap.put("float", "DECIMAL");
        javaType2JdbcMap.put("boolean", "TINYINT");
        javaType2JdbcMap.put("Integer", "INTEGER");
        javaType2JdbcMap.put("Long", "BIGINT");
        javaType2JdbcMap.put("Double", "DECIMAL");
        javaType2JdbcMap.put("Float", "DECIMAL");
        javaType2JdbcMap.put("Boolean", "TINYINT");
        javaType2JdbcMap.put("String", "VARCHAR");
        javaType2JdbcMap.put("BigDecimal", "DECIMAL");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }
}
