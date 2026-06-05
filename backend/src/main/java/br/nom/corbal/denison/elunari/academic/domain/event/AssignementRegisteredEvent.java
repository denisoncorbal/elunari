package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

public class AssignementRegisteredEvent extends BaseAssignementEvent {
    UUID assignementId;

    public AssignementRegisteredEvent(UUID enrollmentId) {
        super();
        this.assignementId = enrollmentId;
    }

    public AssignementRegisteredEvent(UUID correlationId, UUID enrollmentId) {
        super(correlationId);
        this.assignementId = enrollmentId;
    }
}
