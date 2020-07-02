package org.zyx.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zyx.springbootshiro.entity.Account;
import org.zyx.springbootshiro.mapper.AccountMapper;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AccountHandler {

    @Resource
    private AccountMapper accountMapper;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url")String url){
        return url;
    }

    @ResponseBody
    @GetMapping("/findAll")
    public List<Account> findAll(){
        return accountMapper.selectList(null);
    }


    @PostMapping("/login")
    public void login(String username ,String password){


    }


}
