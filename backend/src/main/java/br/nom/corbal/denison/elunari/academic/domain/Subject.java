package br.nom.corbal.denison.elunari.academic.domain;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.valueobjects.SubjectName;
import br.nom.corbal.denison.elunari.academic.domain.valueobjects.SubjectStatus;

public class Subject {
    private final UUID id;
    private SubjectName name;
    private SubjectStatus status;

    public Subject(SubjectName name, SubjectStatus status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = status;
    }

    public UUID getId() {
        return this.id;
    }
}
