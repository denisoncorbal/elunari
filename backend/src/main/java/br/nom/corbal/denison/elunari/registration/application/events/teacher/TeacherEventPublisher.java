package br.nom.corbal.denison.elunari.registration.application.events.teacher;

import br.nom.corbal.denison.elunari.registration.application.events.BaseTeacherEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface TeacherEventPublisher<T extends BaseTeacherEvent> extends BaseEventPublisher<T> {

}
