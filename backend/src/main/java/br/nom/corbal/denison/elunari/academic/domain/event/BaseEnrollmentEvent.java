package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseEnrollmentEvent extends BaseEvent {
    public BaseEnrollmentEvent() {
        super();
    }

    public BaseEnrollmentEvent(UUID correlationId) {
        super(correlationId);
    }
}
