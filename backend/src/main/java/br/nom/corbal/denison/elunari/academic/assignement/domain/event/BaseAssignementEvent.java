package br.nom.corbal.denison.elunari.academic.assignement.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseAssignementEvent extends BaseEvent {
    public BaseAssignementEvent() {
        super();
    }

    public BaseAssignementEvent(UUID correlationId) {
        super(correlationId);
    }
}
