package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

public class SubjectRegisteredEvent extends BaseSubjectEvent {
    UUID subjectId;

    public SubjectRegisteredEvent(UUID subjectId) {
        super();
        this.subjectId = subjectId;
    }

    public SubjectRegisteredEvent(UUID correlationId, UUID subjectId) {
        super(correlationId);
        this.subjectId = subjectId;
    }
}
