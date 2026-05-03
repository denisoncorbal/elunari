package br.nom.corbal.denison.elunari.academic.domain.model.entity;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.Grade;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SchoolClassName;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SchoolClassReferenceYear;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SchoolClassStatus;

public class SchoolClassEntity {
    private UUID id;

    public UUID getId() {
        return id;
    }

    private SchoolClassName name;

    public SchoolClassName getName() {
        return name;
    }

    private Grade grade;
    private SchoolClassReferenceYear referenceYear;
    private SchoolClassStatus status;

    public SchoolClassEntity(SchoolClassName name, Grade grade, SchoolClassReferenceYear referenceYear,
            SchoolClassStatus status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.grade = grade;
        this.referenceYear = referenceYear;
        this.status = status;
    }

}
