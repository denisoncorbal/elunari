package br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseReadingLevelRecordEvent extends BaseEvent {
    public BaseReadingLevelRecordEvent() {
        super();
    }

    public BaseReadingLevelRecordEvent(UUID correlationId) {
        super(correlationId);
    }
}
