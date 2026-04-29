package br.nom.corbal.denison.elunari.registration.application.events;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.events.BaseEvent;

public abstract class BaseTeacherEvent extends BaseEvent {
    public BaseTeacherEvent() {
        super();
    }

    public BaseTeacherEvent(UUID correlationId) {
        super(correlationId);
    }
}
