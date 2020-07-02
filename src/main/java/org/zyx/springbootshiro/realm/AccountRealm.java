package org.zyx.springbootshiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.zyx.springbootshiro.entity.Account;
import org.zyx.springbootshiro.service.AccountService;

import javax.annotation.Resource;

/**
 * Realm 开发者自定义的模块,根据项目的需求,验证和授权的逻辑全部写在Realm中
 *
 *1.配置相关操作 :
 *   1) 先验证登录名,后验证登录密码
 *2.创建配置类,添加 Shiro 配置
 *
 */
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;

    /**授权操作
     * 角色的权限信息集合,授权时使用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {



        return null;
    }

    /**认证操作
     * 用户的角色信息集合,认证时使用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取认证token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account account = accountService.findByUserName(token.getUsername());
        //验证用户名是否存在
        if(account != null){
            //用户存在则返回带有该用户信息的 SimpleAuthenticationInfo, 自动验证密码
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }

        //如果未找到该用户(密码错误),则通过抛出异常的方式进行判断错误信息

        return null;
    }
}
