package com.myprojects.cadclients.enums;

import java.util.ArrayList;
import java.util.Collection;

public enum SexEnum {

    M(1, "Masculino", "M"),
    F(2, "Feminino", "F");

    private final int id;
    private final String sex;
    private final String abbreviation;

    private SexEnum(int id, String sex, String abbreviation) {
        this.id = id;
        this.sex = sex;
        this.abbreviation = abbreviation;
    }

    public int getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static SexEnum convert(String str) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.toString().equals(str)) {
                return sexEnum;
            }
        }
        return null;
    }

    public static Collection<String> strRoles() {
        Collection<String> lstRoles = new ArrayList<>();
        for (SexEnum sexEnum : SexEnum.values()) {
            lstRoles.add(sexEnum.name());
        }
        return lstRoles;
    }

}
