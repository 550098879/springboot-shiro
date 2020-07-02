package org.zyx.springbootshiro.service;

import org.zyx.springbootshiro.entity.Account;

public interface AccountService {
    Account findByUserName(String userName);
}
