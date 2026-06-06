package br.nom.corbal.denison.elunari.academic.enrollment.application.event;

import br.nom.corbal.denison.elunari.academic.enrollment.domain.event.BaseEnrollmentEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface EnrollmentEventPublisher<T extends BaseEnrollmentEvent> extends BaseEventPublisher<T> {

}
