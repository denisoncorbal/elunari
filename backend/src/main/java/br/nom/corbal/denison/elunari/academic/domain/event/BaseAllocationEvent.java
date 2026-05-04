package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseAllocationEvent extends BaseEvent {
    public BaseAllocationEvent() {
        super();
    }

    public BaseAllocationEvent(UUID correlationId) {
        super(correlationId);
    }
}
