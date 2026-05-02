package br.nom.corbal.denison.elunari.registration.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseStudentEvent extends BaseEvent {
    public BaseStudentEvent() {
        super();
    }

    public BaseStudentEvent(UUID correlationId) {
        super(correlationId);
    }
}
