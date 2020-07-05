package org.zyx.springbootshiro.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zyx.springbootshiro.realm.AccountRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro 安全框架配置类
 */

@Configuration
public class ShiroConfig {

    /**
     *  过滤器工厂，Shiro 的基本运行机制是开 发者定制规则，Shiro 去执行，
     *  具体的执行操作就是由 ShiroFilterFactoryBean 创建的一个个 Filter 对象来完成。
     * @param manager securityManager()方法注入的bean
     * @return  工厂bean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);

        //权限设置
        Map<String,String> map = new HashMap<>();
        map.put("/main","authc");//必须认证才可以进入
        map.put("/manage","perms[manage]");//必须拥有权限才可以进入(多个权限)
        map.put("/admin","roles[admin]");//必须拥有角色才可以进入(多个角色)

        //设置过滤器链初始化map集合,通过map设置过滤器
        factoryBean.setFilterChainDefinitionMap(map);

        //修改默认设置
        //修改登录界面
        factoryBean.setLoginUrl("/login");
        //修改未授权登录跳转的页面
        factoryBean.setUnauthorizedUrl("/unauth");

        return factoryBean;
    }
    /**
     * 将安全管理器注入到IOC中,开发者自定义的Realm需要注入到 DefaultWebSecurityManager
     * 进行管理才能生效
     * @Qualifier("name") 制定方法名区IOC中查找bean
     */
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }
    @Bean  //将Realm权限操作添加到IOC容器中,默认使用方法名作为bean的名字
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }


}
