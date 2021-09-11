package top.breezes.model;

import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/11 12:21
 * @description
 */
public class MapperFile {

    private static final String JAVA = ".java";

    private String packageName;

    private String fileName;

    private String className;

    private List<String> docList;

    private String filePtah;

    public MapperFile(
            String packages, List<String> docList, JavaClass javaClass, String outDir) {
        this.setClassName(javaClass.getSimpleName() + "Mapper");
        this.setFileName(javaClass.getSimpleName() + "Mapper" + JAVA);
        this.setPackageName(packages);
        this.setDocList(docList);
        String packagePath = StringUtils.join(packages.split("\\."), File.separator);
        this.setFilePtah(outDir + File.separator + packagePath + File.separator + this.fileName);
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

    public List<String> getDocList() {
        return docList;
    }

    public void setDocList(List<String> docList) {
        this.docList = docList;
    }

    public String getFilePtah() {
        return filePtah;
    }

    public void setFilePtah(String filePtah) {
        this.filePtah = filePtah;
    }
}
