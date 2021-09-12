package top.breezes.model.mysql;

import com.google.common.base.CaseFormat;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.lang3.StringUtils;
import top.breezes.model.Field;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/11 14:11
 * @description
 */
public class MysqlScriptFile {

    public static final String SQL = ".sql";

    private List<Field> fieldList;

    private String fileName;

    private String filePath;

    private String tableName;

    private String tableComment;

    public MysqlScriptFile(JavaClass javaClass, String outDir) {
        this.setFileName(javaClass.getSimpleName() + SQL);
        this.setFilePath(outDir + File.separator + "db" + File.separator + "mysql" +
                File.separator + this.fileName);
        this.fieldList = buildFieldList(javaClass);
        this.tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, javaClass.getName());
        this.tableComment = StringUtils.isBlank(javaClass.getComment()) ? StringUtils.EMPTY
                : javaClass.getComment().replaceAll("\r\n|\r|\n", " ");
    }

    private List<Field> buildFieldList(JavaClass javaClass) {
        return javaClass.getFields()
                .parallelStream()
                .filter(field -> !"serialVersionUID".equals(field.getName()))
                .map(field -> Field.create(field, MysqlMapperXmlFile.javaType2JdbcMap, MysqlMapperXmlFile.jdbcLengthMap))
                .collect(Collectors.toList());
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static String getSQL() {
        return SQL;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
