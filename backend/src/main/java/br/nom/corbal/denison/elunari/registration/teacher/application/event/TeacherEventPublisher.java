package br.nom.corbal.denison.elunari.registration.teacher.application.event;

import br.nom.corbal.denison.elunari.registration.teacher.domain.event.BaseTeacherEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface TeacherEventPublisher<T extends BaseTeacherEvent> extends BaseEventPublisher<T> {

}
