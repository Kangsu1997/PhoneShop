package com.assia.model.user;

import com.assia.domain.user.Role;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.math.BigInteger;

@Data
public class UserForm {
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    private String confirmPassword;
    @NotBlank
    private String fullName;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private String sex;
    @NotBlank
    private String email;

    private Role role;

}
