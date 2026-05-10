package br.nom.corbal.denison.elunari.academic.application.mapper;

import br.nom.corbal.denison.elunari.academic.application.command.EnterSpecialEducationStudentRecordCommand;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.SpecialEducationStudentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SpecialEducationStudentInformation;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SpecialEducationStudentStatus;

public class SpecialEducationStudentRecordMapper {
    public SpecialEducationStudentRecordAggregate from(
            EnterSpecialEducationStudentRecordCommand enterSpecialEducationStudentRecordCommand) {
        return new SpecialEducationStudentRecordAggregate(
                enterSpecialEducationStudentRecordCommand.teacherId(),
                enterSpecialEducationStudentRecordCommand.studentId(),
                enterSpecialEducationStudentRecordCommand.schoolClassId(),
                new SpecialEducationStudentInformation(enterSpecialEducationStudentRecordCommand.information()),
                new SpecialEducationStudentStatus(enterSpecialEducationStudentRecordCommand.status()));
    }
}
