package br.nom.corbal.denison.elunari.academic.application.events;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.events.BaseEvent;

public abstract class BaseSubjectEvent extends BaseEvent {
    public BaseSubjectEvent() {
        super();
    }

    public BaseSubjectEvent(UUID correlationId) {
        super(correlationId);
    }
}
