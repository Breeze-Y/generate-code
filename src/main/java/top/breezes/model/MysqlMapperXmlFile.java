package top.breezes.model;

import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/11 13:10
 * @description
 */
public class MysqlMapperXmlFile {

    private static final String XML = ".xml";

    private String packageName;

    private String fileName;

    private String className;

    private String mapperName;

    private String mapperFullyQualifiedName;

    private String classFullyQualifiedName;

    private String filePtah;

    private List<Field> fieldList;

    public MysqlMapperXmlFile(String packages, JavaClass javaClass, String outDir) {
        this.setClassName(javaClass.getSimpleName());
        this.setFileName(javaClass.getSimpleName() + "Mapper" + XML);
        this.setMapperName(javaClass.getSimpleName() + "Mapper");
        this.setMapperFullyQualifiedName(packages + "." + this.mapperName);
        this.setClassFullyQualifiedName(javaClass.getGenericFullyQualifiedName());
        this.setPackageName(packages);
        String packagePath = StringUtils.join(packages.split("\\."), File.separator);
        this.setFilePtah(outDir + File.separator + packagePath + File.separator + "mysql" +
                File.separator + this.fileName);
        this.fieldList = buildFieldList(javaClass);
    }

    private List<Field> buildFieldList(JavaClass javaClass) {
        return javaClass.getFields()
                .parallelStream()
                .filter(field -> !"serialVersionUID".equals(field.getName()))
                .map(Field::create)
                .collect(Collectors.toList());
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

    public String getFilePtah() {
        return filePtah;
    }

    public void setFilePtah(String filePtah) {
        this.filePtah = filePtah;
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
