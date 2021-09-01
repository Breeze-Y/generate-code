package top.breezes.config.template.normal;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 20:59
 * @description 基础模板父类
 */
public class BaseTemplate {

    /**
     * 指定模板是否参与构建，缺省true表示参与
     */
    private Boolean generate = Boolean.TRUE;

    /**
     * 包名
     * <p>
     * dao: com.breezes.mapper
     * service: com.breezes.service
     * </p>
     */
    private String packages;

    public Boolean getGenerate() {
        return generate;
    }

    public void setGenerate(Boolean generate) {
        this.generate = generate;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }
}
