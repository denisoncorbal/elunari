package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseTeacherNoteRecordEvent extends BaseEvent {
    public BaseTeacherNoteRecordEvent() {
        super();
    }

    public BaseTeacherNoteRecordEvent(UUID correlationId) {
        super(correlationId);
    }
}
