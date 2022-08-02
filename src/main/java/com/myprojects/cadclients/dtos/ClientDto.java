package com.myprojects.cadclients.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myprojects.cadclients.enums.SexEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto implements Serializable {

    private static final long serialVersionUID = 1534244992322023678L;

    @CPF
    @NotBlank(message = "O campo 'CPF' deve ser preenchido!")
    private String cpf;

    @NotBlank(message = "O campo 'Name' deve ser preenchido!")
    private String name;

    private SexEnum sex;

    @Email
    private String email;

    private String naturality;
    private String nacionality;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtBirthday;

}
