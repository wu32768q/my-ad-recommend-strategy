package org.example.bstest.demos.web.entity;

import lombok.Data;
import org.example.bstest.demos.web.enums.UserRoleEnum;

@Data
public class UserEntity {

    String userName;

    String password;

    String createTime;

    UserRoleEnum userRoleEnum;


}
