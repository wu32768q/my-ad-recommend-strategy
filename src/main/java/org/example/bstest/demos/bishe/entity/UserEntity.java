package org.example.bstest.demos.bishe.entity;

import lombok.Data;
import org.example.bstest.demos.bishe.enums.UserRoleEnum;

@Data
public class UserEntity {

    String userName;

    String password;

    String createTime;

    UserRoleEnum userRoleEnum;


}
