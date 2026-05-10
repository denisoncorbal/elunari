package br.nom.corbal.denison.elunari.academic.application.event.specialeducationstudentrecord;

import br.nom.corbal.denison.elunari.academic.domain.event.BaseSpecialEducationStudentRecordEvent;
import br.nom.corbal.denison.elunari.shared.application.events.BaseEventPublisher;

public interface SpecialEducationStudentRecordEventPublisher<T extends BaseSpecialEducationStudentRecordEvent>
        extends BaseEventPublisher<T> {

}
