package com.luizfiliperm.pms.entities.Info.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IdentificationType {
    CPF("cpf"),
    RG("rg"),
    CNPJ("cnpj"),
    PASSPORT("passport");

    private String type;

}
