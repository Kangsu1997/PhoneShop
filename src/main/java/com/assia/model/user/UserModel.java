package com.assia.model.user;

import com.assia.domain.user.Role;
import lombok.Data;

import java.math.BigInteger;

@Data
public class UserModel {
    private BigInteger id;
    private String username;
    private String password;
    private String confirmPassword;
    private String fullname;
    private String birth_day;
    private String phone;
    private String sex;
    private String email;
    private String address;
    private Role role;
}
