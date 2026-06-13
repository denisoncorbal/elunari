package br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.event;

import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.event.BaseReadingLevelRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface ReadingLevelRecordEventPublisher<T extends BaseReadingLevelRecordEvent>
        extends BaseEventPublisher<T> {
}
