package org.zyx.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zyx.springbootshiro.entity.Account;
import org.zyx.springbootshiro.mapper.AccountMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AccountHandler {

    @Resource
    private AccountMapper accountMapper;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<Account> findAll() {
        return accountMapper.selectList(null);
    }


    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        //验证登录授权

        //1.获取Subject 用户信息对象
        Subject subject = SecurityUtils.getSubject();
        //2.封装一个UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3.将token传到subject中,执行登录操作,
        // 不同的情况会抛出不同的异常,捕获异常就知道是账号错误还是密码错误了

        try {
            subject.login(token);
            Account account = (Account) subject.getPrincipal();
            //登录成功,将用户的登录信息存储到session中
            subject.getSession().setAttribute("account",account);
            return "index";
        } catch (UnknownAccountException e) {//账号不存在异常
            System.out.println("用户名不存在");
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {//密码错误抛出的异常
            System.out.println("密码错误");
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/unauth")
    public String unauth(){
        return "您还未得到授权,无法访问";
    }

    @GetMapping("/logout")
    public String logout(){
        //调用Shiro的方法退出登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }





}
