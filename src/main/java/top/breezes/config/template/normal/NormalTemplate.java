package top.breezes.config.template.normal;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 22:39
 * @description
 */
public class NormalTemplate {
    /**
     * 持久层配置
     */
    private DaoTemplate dao;
    /**
     * 业务层配置
     */
    private ServiceTemplate service;


    public boolean isDaoEnabled() {
        return dao != null && dao.getGenerate();
    }

    public boolean isServiceEnabled() {
        return service != null && service.getGenerate();
    }

    public ServiceTemplate getService() {
        return service;
    }

    public void setService(ServiceTemplate service) {
        this.service = service;
    }

    public DaoTemplate getDao() {
        return dao;
    }

    public void setDao(DaoTemplate dao) {
        this.dao = dao;
    }
}
