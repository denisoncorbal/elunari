package br.nom.corbal.denison.elunari.academic.application.event.enrollment;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseEnrollmentEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface EnrollmentEventPublisher<T extends BaseEnrollmentEvent> extends BaseEventPublisher<T> {

}
