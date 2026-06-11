package br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.application.mapper;

import br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.application.command.EnterSpecialEducationStudentRecordCommand;
import br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.model.aggregate.SpecialEducationStudentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.model.valueobject.SpecialEducationStudentInformation;
import br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.model.valueobject.SpecialEducationStudentStatus;

public final class SpecialEducationStudentRecordMapper {
    private SpecialEducationStudentRecordMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static SpecialEducationStudentRecordAggregate toSpecialEducationStudentRecordAggregate(
            EnterSpecialEducationStudentRecordCommand enterSpecialEducationStudentRecordCommand) {
        return new SpecialEducationStudentRecordAggregate(
                enterSpecialEducationStudentRecordCommand.teacherId(),
                enterSpecialEducationStudentRecordCommand.studentId(),
                enterSpecialEducationStudentRecordCommand.schoolClassId(),
                new SpecialEducationStudentInformation(enterSpecialEducationStudentRecordCommand.information()),
                new SpecialEducationStudentStatus(enterSpecialEducationStudentRecordCommand.status()));
    }
}
