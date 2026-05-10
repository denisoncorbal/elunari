package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import java.util.List;

public record SpecialEducationStudentInformation(String information) {
    public SpecialEducationStudentInformation {
        if (!List.of(
                "RED FLAG",
                "DIAGNOSED",
                "SSP",
                "IEP").contains(information)) {
            throw new IllegalArgumentException("Invalid information");
        }
    }
}
