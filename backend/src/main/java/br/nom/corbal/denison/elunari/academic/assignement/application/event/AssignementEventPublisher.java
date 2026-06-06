package br.nom.corbal.denison.elunari.academic.assignement.application.event;

import br.nom.corbal.denison.elunari.academic.assignement.domain.event.BaseAssignementEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface AssignementEventPublisher<T extends BaseAssignementEvent>
                extends BaseEventPublisher<T> {

}
