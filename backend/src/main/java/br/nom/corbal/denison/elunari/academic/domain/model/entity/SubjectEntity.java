package br.nom.corbal.denison.elunari.academic.domain.model.entity;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectName;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectStatus;

public class SubjectEntity {
    private final UUID id;
    private SubjectName name;
    private SubjectStatus status;

    public SubjectEntity(SubjectName name, SubjectStatus status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = status;
    }

    public UUID getId() {
        return this.id;
    }
}
