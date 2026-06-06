package br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject;

import java.time.LocalDate;

public record SchoolClassReferenceYear(int referenceYear) {
    public SchoolClassReferenceYear {
        if (referenceYear < LocalDate.MIN.getYear() || referenceYear > LocalDate.MAX.getYear()) {
            throw new IllegalArgumentException("Reference year outside of valid range");
        }
    }

}
