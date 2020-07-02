package org.zyx.springbootshiro.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;
    private String username;
    private String password;
    private String perms;//权限
    private String role;//角色
}
