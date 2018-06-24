package com.assia.domain.user;

import com.assia.model.user.UserModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(name = "username",unique = true)
    private String username;
    private String password;
    @Column(name = "confirm_password")
    private String confirmPassword;
    @Column(name = "full_name")
    private String fullName;
    private String birth_day;
    private String phone;
    private String sex;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.confirmPassword = user.getConfirmPassword();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.birth_day = user.getBirth_day();
        this.address = user.getAddress();
    }

    public User() {

    }

    public com.assia.model.user.UserModel toUser() {
        com.assia.model.user.UserModel rs = new UserModel();
        rs.setId(id);
        rs.setUsername(username);
        rs.setPassword(password);
        rs.setConfirmPassword(confirmPassword);
        rs.setFullname(fullName);
        rs.setAddress(address);
        rs.setEmail(email);
        rs.setBirth_day(birth_day);
        rs.setPhone(phone);
        rs.setSex(sex);
        rs.setRole(role);
        return rs;
    }
}
