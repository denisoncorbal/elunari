package br.nom.corbal.denison.elunari.registration.application.events.student;

import br.nom.corbal.denison.elunari.registration.application.events.BaseStudentEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface StudentEventPublisher<T extends BaseStudentEvent> extends BaseEventPublisher<T> {
}
