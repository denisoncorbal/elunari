package br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.application.event;

import br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.event.BaseSpecialEducationStudentRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SpecialEducationStudentRecordEventPublisher<T extends BaseSpecialEducationStudentRecordEvent>
        extends BaseEventPublisher<T> {

}
