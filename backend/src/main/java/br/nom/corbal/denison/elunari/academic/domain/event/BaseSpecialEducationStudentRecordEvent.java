package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseSpecialEducationStudentRecordEvent extends BaseEvent {
    public BaseSpecialEducationStudentRecordEvent() {
        super();
    }

    public BaseSpecialEducationStudentRecordEvent(UUID correlationId) {
        super(correlationId);
    }
}
