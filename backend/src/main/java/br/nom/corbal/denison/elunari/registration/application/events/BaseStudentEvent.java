package br.nom.corbal.denison.elunari.registration.application.events;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.events.BaseEvent;

public abstract class BaseStudentEvent extends BaseEvent {
    public BaseStudentEvent() {
        super();
    }

    public BaseStudentEvent(UUID correlationId) {
        super(correlationId);
    }
}
