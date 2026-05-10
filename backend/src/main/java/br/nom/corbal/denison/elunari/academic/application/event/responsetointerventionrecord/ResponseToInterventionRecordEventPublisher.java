package br.nom.corbal.denison.elunari.academic.application.event.responsetointerventionrecord;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseResponseToInterventionRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface ResponseToInterventionRecordEventPublisher<T extends BaseResponseToInterventionRecordEvent>
        extends BaseEventPublisher<T> {

}
