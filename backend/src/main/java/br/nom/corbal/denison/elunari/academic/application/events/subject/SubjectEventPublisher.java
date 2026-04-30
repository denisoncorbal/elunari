package br.nom.corbal.denison.elunari.academic.application.events.subject;

import br.nom.corbal.denison.elunari.academic.application.events.BaseSubjectEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SubjectEventPublisher<T extends BaseSubjectEvent> extends BaseEventPublisher<T> {

}
