package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseSchoolClassEvent extends BaseEvent {
    public BaseSchoolClassEvent() {
        super();
    }

    public BaseSchoolClassEvent(UUID correlationId) {
        super(correlationId);
    }
}
