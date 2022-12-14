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
import javax.persistence.Transient;

import com.myprojects.cadclients.enums.SexEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(nullable = false)
    private LocalDate dtBirthday;
    @Column(nullable = false)
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    @Transient
    private String cpfFormatado;

}
