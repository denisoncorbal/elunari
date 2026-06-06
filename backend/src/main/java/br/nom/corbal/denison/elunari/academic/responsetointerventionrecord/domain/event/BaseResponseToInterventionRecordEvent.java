package br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseResponseToInterventionRecordEvent extends BaseEvent {
    public BaseResponseToInterventionRecordEvent() {
        super();
    }

    public BaseResponseToInterventionRecordEvent(UUID correlationId) {
        super(correlationId);
    }
}
