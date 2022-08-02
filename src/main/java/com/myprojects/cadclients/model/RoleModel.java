package com.myprojects.cadclients.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.myprojects.cadclients.enums.RolesEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ROLE")
public class RoleModel implements GrantedAuthority {

    private static final long serialVersionUID = -6186040511096479401L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RolesEnum roleName;

    @Override
    public String getAuthority() {
        return this.roleName.getRoleName();
    }

}
