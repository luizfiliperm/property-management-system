package com.luizfiliperm.pms.entities.Info;

import com.luizfiliperm.pms.entities.Info.enums.IdentificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "identification_type")
    @Enumerated(EnumType.STRING)
    private IdentificationType type;

    @Column(name = "document_id")
    private String documentId;

    @OneToOne(mappedBy = "identification", cascade = CascadeType.ALL, orphanRemoval = true)
    PersonalInformation personalInformation;

}
