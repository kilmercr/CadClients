package com.myprojects.cadclients.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myprojects.cadclients.enums.SexEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CLIENT")
public class ClientModel implements Serializable {

    private static final long serialVersionUID = 1953744992327824598L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    private String email;
    private String naturality;
    private String nacionality;
    private LocalDate dtBirthday;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

}
