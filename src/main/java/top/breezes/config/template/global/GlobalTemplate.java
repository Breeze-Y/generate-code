package top.breezes.config.template.global;

import java.util.LinkedHashMap;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 22:37
 * @description 全局模板配置
 */
public class GlobalTemplate {

    /**
     * java doc配置封装
     */
    private LinkedHashMap<String, String> doc;

    public LinkedHashMap<String, String> getDoc() {
        return doc;
    }

    public void setDoc(LinkedHashMap<String, String> doc) {
        this.doc = doc;
    }
}
