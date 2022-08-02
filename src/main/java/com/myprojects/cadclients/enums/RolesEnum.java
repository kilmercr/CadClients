package com.myprojects.cadclients.enums;

import java.util.ArrayList;
import java.util.Collection;

public enum RolesEnum {

    ROLE_ADMIN(1, "ROLE_ADMIN"),
    ROLE_USER(2, "ROLE_USER");

    private int id;
    private String roleName;

    private RolesEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RolesEnum convert(String str) {
        for (RolesEnum roleEnum : RolesEnum.values()) {
            if (roleEnum.toString().equals(str)) {
                return roleEnum;
            }
        }
        return null;
    }

    public static Collection<String> strRoles() {
        Collection<String> lstRoles = new ArrayList<>();
        for (RolesEnum roleEnum : RolesEnum.values()) {
            lstRoles.add(roleEnum.name());
        }
        return lstRoles;
    }

}
