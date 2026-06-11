package br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.mapper;

import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.application.command.EnterResponseToInterventionRecordCommand;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.model.aggregate.ResponseToInterventionRecordAggregate;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.model.valueobject.ResponseToInterventionLevel;
import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.model.valueobject.ResponseToInterventionStatus;

public final class ResponseToInterventionRecordMapper {
    private ResponseToInterventionRecordMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static ResponseToInterventionRecordAggregate toResponseToInterventionRecordAggregate(
            EnterResponseToInterventionRecordCommand enterResponseToInterventionRecordCommand) {
        return new ResponseToInterventionRecordAggregate(
                enterResponseToInterventionRecordCommand.teacherId(),
                enterResponseToInterventionRecordCommand.studentId(),
                enterResponseToInterventionRecordCommand.schoolClassId(),
                new ResponseToInterventionLevel(enterResponseToInterventionRecordCommand.level()),
                new ResponseToInterventionStatus(enterResponseToInterventionRecordCommand.status()));
    }
}
