package br.nom.corbal.denison.elunari.academic.schoolclass.application.event;

import br.nom.corbal.denison.elunari.academic.schoolclass.domain.event.BaseSchoolClassEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SchoolClassEventPublisher<T extends BaseSchoolClassEvent> extends BaseEventPublisher<T> {

}
