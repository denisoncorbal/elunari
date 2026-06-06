package br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.event;

import java.util.UUID;

public class ResponseToInterventionRecordEnteredEvent extends BaseResponseToInterventionRecordEvent {
    UUID responseToInterventionRecordId;

    public ResponseToInterventionRecordEnteredEvent(UUID responseToInterventionRecordId) {
        super();
        this.responseToInterventionRecordId = responseToInterventionRecordId;
    }

    public ResponseToInterventionRecordEnteredEvent(UUID correlationId, UUID responseToInterventionRecordId) {
        super(correlationId);
        this.responseToInterventionRecordId = responseToInterventionRecordId;
    }
}
