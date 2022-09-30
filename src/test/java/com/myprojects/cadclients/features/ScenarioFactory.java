package com.myprojects.cadclients.features;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.myprojects.cadclients.dtos.ClientDto;
import com.myprojects.cadclients.enums.SexEnum;
import com.myprojects.cadclients.utils.StringUtils;

public class ScenarioFactory {

    public static final String CLIENT_DEFAULT_NAME = "CLIENT_TST_AUTOMAT.";

    public static String getTestSufix() {
        return String.valueOf(Date.from(Instant.now()).getTime());
    }

    public static ClientDto createClientDto() {
        return ClientDto.builder().cpf(StringUtils.formatCPF("26250236350"))
                .name(CLIENT_DEFAULT_NAME + "_" + getTestSufix()).sex(SexEnum.F).email("client3@tst.com.br")
                .naturality("Curitiba").nacionality("Brasil")
                .dtBirthday(LocalDate.now(ZoneId.systemDefault()).minusYears(20L)).build();
    }
}
