package br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseHonorRollRecordEvent extends BaseEvent {
    public BaseHonorRollRecordEvent() {
        super();
    }

    public BaseHonorRollRecordEvent(UUID correlationId) {
        super(correlationId);
    }
}
