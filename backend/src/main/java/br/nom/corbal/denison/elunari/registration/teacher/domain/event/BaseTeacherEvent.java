package br.nom.corbal.denison.elunari.registration.teacher.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseTeacherEvent extends BaseEvent {
    public BaseTeacherEvent() {
        super();
    }

    public BaseTeacherEvent(UUID correlationId) {
        super(correlationId);
    }
}
