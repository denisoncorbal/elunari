package br.nom.corbal.denison.elunari.academic.subject.application.event;

import br.nom.corbal.denison.elunari.academic.subject.domain.event.BaseSubjectEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SubjectEventPublisher<T extends BaseSubjectEvent> extends BaseEventPublisher<T> {

}
