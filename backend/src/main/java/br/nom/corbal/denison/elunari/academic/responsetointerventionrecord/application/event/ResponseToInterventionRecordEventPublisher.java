package br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.event;

import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.event.BaseResponseToInterventionRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface ResponseToInterventionRecordEventPublisher<T extends BaseResponseToInterventionRecordEvent>
        extends BaseEventPublisher<T> {

}
