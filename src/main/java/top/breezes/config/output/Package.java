package top.breezes.config.output;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 21:10
 * @description 包名封装
 */
public class Package {

    /**
     * 持久层包名
     * <p>
     * example:top.breezes.mapper
     * </p>
     */
    private String dao;

    /**
     * 业务层包名
     * <p>
     * example:top.breezes.service
     * </p>
     */
    private String service;

    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
