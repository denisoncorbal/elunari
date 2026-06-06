package br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.event;

import java.util.UUID;

public class SpecialEducationStudentRecordEnteredEvent extends BaseSpecialEducationStudentRecordEvent {
    UUID specialEducationStudentRecordId;

    public SpecialEducationStudentRecordEnteredEvent(UUID specialEducationStudentRecordId) {
        super();
        this.specialEducationStudentRecordId = specialEducationStudentRecordId;
    }

    public SpecialEducationStudentRecordEnteredEvent(UUID correlationId, UUID specialEducationStudentRecordId) {
        super(correlationId);
        this.specialEducationStudentRecordId = specialEducationStudentRecordId;
    }
}
