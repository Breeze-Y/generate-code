package top.breezes.model;

import com.google.common.base.CaseFormat;
import com.thoughtworks.qdox.model.JavaField;
import org.apache.commons.lang3.StringUtils;

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

    private String length;

    private String comment;

    public static Field create(JavaField field, Map<String, String> typeMap, Map<String, String> lengthMap) {
        Field newField = new Field();
        newField.setId("id".equals(field.getName()) || "uuid".equals(field.getName()));
        newField.setName(field.getName());
        newField.setColumn(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()));
        newField.setJdbcType(typeMap.getOrDefault(field.getType().getName(), typeMap.get("String")));
        newField.setLength(lengthMap.getOrDefault(field.getType().getName(), typeMap.get("String")));
        newField.setComment(StringUtils.isBlank(field.getComment()) ? StringUtils.EMPTY : field.getComment().replaceAll("\r\n|\r|\n", " "));
        return newField;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
