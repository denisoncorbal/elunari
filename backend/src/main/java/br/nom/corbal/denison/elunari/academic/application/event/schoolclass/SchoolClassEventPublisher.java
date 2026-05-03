package br.nom.corbal.denison.elunari.academic.application.event.schoolclass;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseSchoolClassEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SchoolClassEventPublisher<T extends BaseSchoolClassEvent> extends BaseEventPublisher<T> {

}
