package top.breezes.config.template.normal;

import top.breezes.config.template.normal.BaseTemplate;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 21:02
 * @description service层模板配置
 */
public class ServiceTemplate extends BaseTemplate {

    /**
     * 指定是否需要为service生成接口
     * 为true则表示生成, 缺省true
     * <p>
     * example: HelloServiceImpl implements HelloService
     * </p>
     */
    private Boolean needInterface = Boolean.TRUE;


    public Boolean getNeedInterface() {
        return needInterface;
    }

    public void setNeedInterface(Boolean needInterface) {
        this.needInterface = needInterface;
    }
}
