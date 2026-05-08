package br.nom.corbal.denison.elunari.academic.domain.event;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.domain.event.BaseEvent;

public abstract class BaseEnglishLanguageDevelopmentRecordEvent extends BaseEvent {
    public BaseEnglishLanguageDevelopmentRecordEvent() {
        super();
    }

    public BaseEnglishLanguageDevelopmentRecordEvent(UUID correlationId) {
        super(correlationId);
    }
}
