package org.zyx.springbootshiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.springbootshiro.entity.Account;
import org.zyx.springbootshiro.mapper.AccountMapper;
import org.zyx.springbootshiro.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUserName(String userName) {

        return accountMapper.selectOne(new QueryWrapper<Account>().eq("username",userName));
    }
}
