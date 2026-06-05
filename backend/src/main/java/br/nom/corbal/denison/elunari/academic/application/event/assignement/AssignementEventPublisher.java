package br.nom.corbal.denison.elunari.academic.application.event.assignement;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseAssignementEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface AssignementEventPublisher<T extends BaseAssignementEvent>
                extends BaseEventPublisher<T> {

}
