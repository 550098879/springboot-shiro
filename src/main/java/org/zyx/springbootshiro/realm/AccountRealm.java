package org.zyx.springbootshiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.zyx.springbootshiro.entity.Account;
import org.zyx.springbootshiro.service.AccountService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

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

        //1.获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();//获取account信息,认证方法中传递的account参数
        //2.设置角色
        Set<String> roles = new HashSet<>();//防止重复
        roles.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //3.设置权限
        info.addStringPermission(account.getPerms());

        //4.注意需要将info返回,否则无法生效
        return info;
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
