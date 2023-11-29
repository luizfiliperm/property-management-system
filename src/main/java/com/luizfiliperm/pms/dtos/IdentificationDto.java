package com.luizfiliperm.pms.dtos;

import com.luizfiliperm.pms.entities.Info.Identification;
import com.luizfiliperm.pms.entities.Info.enums.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdentificationDto {
    private IdentificationType identificationType;
    private String documentId;

    public IdentificationDto(Identification identification){
        this.identificationType = identification.getType();
        this.documentId = identification.getDocumentId();
    }

    public Identification convertToIdentification(){
        Identification identification = new Identification();
        identification.setType(this.identificationType);
        identification.setDocumentId(this.documentId);
        return identification;
    }
}
