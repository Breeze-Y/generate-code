package top.breezes.model.pgsql;

import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.lang3.StringUtils;
import top.breezes.model.Field;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/11 13:10
 * @description
 */
public class PgsqlMapperXmlFile {

    private static final String XML = ".xml";

    private String packageName;

    private String fileName;

    private String className;

    private String mapperName;

    private String mapperFullyQualifiedName;

    private String classFullyQualifiedName;

    private String filePath;

    private List<Field> fieldList;

    public PgsqlMapperXmlFile(String packages, JavaClass javaClass, String outDir) {
        this.setClassName(javaClass.getSimpleName());
        this.setFileName(javaClass.getSimpleName() + "Mapper" + XML);
        this.setMapperName(javaClass.getSimpleName() + "Mapper");
        this.setMapperFullyQualifiedName(packages + "." + this.mapperName);
        this.setClassFullyQualifiedName(javaClass.getGenericFullyQualifiedName());
        this.setPackageName(packages);
        String packagePath = StringUtils.join(packages.split("\\."), File.separator);
        this.setFilePath(outDir + File.separator + packagePath + File.separator + "xml" +
                File.separator + this.fileName);
        this.fieldList = buildFieldList(javaClass);
    }

    private List<Field> buildFieldList(JavaClass javaClass) {
        return javaClass.getFields()
                .parallelStream()
                .filter(field -> !"serialVersionUID".equals(field.getName()))
                .map(field -> Field.create(field, javaType2JdbcMap, jdbcLengthMap))
                .collect(Collectors.toList());
    }

    public static Map<String, String> javaType2JdbcMap = new HashMap<>();
    public static Map<String, String> jdbcLengthMap = new HashMap<>();

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
        javaType2JdbcMap.put("Date", "TIMESTAMP");

        jdbcLengthMap.put("int", "int4");
        jdbcLengthMap.put("long", "int8");
        jdbcLengthMap.put("double", "float8");
        jdbcLengthMap.put("float", "float4");
        jdbcLengthMap.put("boolean", "bool");
        jdbcLengthMap.put("Integer", "int4");
        jdbcLengthMap.put("Long", "int8");
        jdbcLengthMap.put("Double", "float8");
        jdbcLengthMap.put("Float", "float4");
        jdbcLengthMap.put("Boolean", "bool");
        jdbcLengthMap.put("String", "varchar(255)");
        jdbcLengthMap.put("BigDecimal", "numeric");
        jdbcLengthMap.put("Date", "timestamp(6)");
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public static String getXML() {
        return XML;
    }

    public String getMapperFullyQualifiedName() {
        return mapperFullyQualifiedName;
    }

    public void setMapperFullyQualifiedName(String mapperFullyQualifiedName) {
        this.mapperFullyQualifiedName = mapperFullyQualifiedName;
    }

    public String getClassFullyQualifiedName() {
        return classFullyQualifiedName;
    }

    public void setClassFullyQualifiedName(String classFullyQualifiedName) {
        this.classFullyQualifiedName = classFullyQualifiedName;
    }
}
