package org.zyx.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.zyx.springbootshiro.entity.Account;

@Repository  //接口无法注入,实现类才可以被注入
public interface AccountMapper extends BaseMapper<Account> {



}
