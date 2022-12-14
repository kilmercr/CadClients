package com.myprojects.cadclients.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myprojects.cadclients.enums.SexEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto implements Serializable {

    private static final long serialVersionUID = 1534244992322023678L;

    @CPF(message = "CPF inválido!")
    @NotBlank(message = "O campo 'CPF' deve ser preenchido!")
    private String cpf;

    @NotBlank(message = "O campo 'Name' deve ser preenchido!")
    private String name;

    private SexEnum sex;

    @Email
    private String email;

    private String naturality;
    private String nacionality;

    @NotNull(message = "O campo 'Data de Nascimento' deve ser preenchido!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtBirthday;

}
