package br.nom.corbal.denison.elunari.academic.teachernoterecord.application.event;

import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.event.BaseTeacherNoteRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface TeacherNoteRecordEventPublisher<T extends BaseTeacherNoteRecordEvent>
        extends BaseEventPublisher<T> {

}
