package com.assia.domain.user;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String role;

    public com.assia.model.user.role.Role toRoleModel(){
        com.assia.model.user.role.Role rs = new com.assia.model.user.role.Role();
        rs.setId(2);
        rs.setRole(role);
        return rs;
    }

    public com.assia.model.user.role.RoleForm toRoleForm(){
        com.assia.model.user.role.RoleForm rs = new  com.assia.model.user.role.RoleForm();

        rs.setRole(role);
        return rs;
    }

}
