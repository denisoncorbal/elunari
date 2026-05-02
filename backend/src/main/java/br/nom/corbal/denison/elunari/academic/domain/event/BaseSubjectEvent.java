package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseSubjectEvent extends BaseEvent {
    public BaseSubjectEvent() {
        super();
    }

    public BaseSubjectEvent(UUID correlationId) {
        super(correlationId);
    }
}
