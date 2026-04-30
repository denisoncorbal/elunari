package br.nom.corbal.denison.elunari.academic.application.events.subject;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.events.BaseSubjectEvent;

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
