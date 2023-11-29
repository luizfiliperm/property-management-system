package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Info.Identification;
import com.luizfiliperm.pms.entities.Info.enums.IdentificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdentificationDto {

    @NotBlank(message = "The identification type is required")
    @Pattern(regexp = "^(CPF|CNPJ|PASSPORT|RG)$", message = "Invalid identification type, the available types are: CPF, CNPJ, PASSPORT, RG")
    private String identificationType;

    @NotBlank(message = "The document id is required")
    private String documentId;

    public IdentificationDto(Identification identification){
        this.identificationType = identification.getType().getType();
        this.documentId = identification.getDocumentId();
    }

    public Identification convertToIdentification(){
        Identification identification = new Identification();
        identification.setType(IdentificationType.valueOf(this.identificationType));
        identification.setDocumentId(this.documentId);
        return identification;
    }
}
