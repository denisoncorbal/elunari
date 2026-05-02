package br.nom.corbal.denison.elunari.academic.application.event.subject;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseSubjectEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SubjectEventPublisher<T extends BaseSubjectEvent> extends BaseEventPublisher<T> {

}
