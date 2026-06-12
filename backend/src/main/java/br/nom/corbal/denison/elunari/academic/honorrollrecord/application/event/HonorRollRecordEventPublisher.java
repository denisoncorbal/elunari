package br.nom.corbal.denison.elunari.academic.honorrollrecord.application.event;

import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.event.BaseHonorRollRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface HonorRollRecordEventPublisher<T extends BaseHonorRollRecordEvent>
        extends BaseEventPublisher<T> {

}
