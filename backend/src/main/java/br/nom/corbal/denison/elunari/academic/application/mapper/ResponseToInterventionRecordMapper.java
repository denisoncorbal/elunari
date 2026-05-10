package br.nom.corbal.denison.elunari.academic.application.mapper;

import br.nom.corbal.denison.elunari.academic.application.command.EnterResponseToInterventionRecordCommand;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.ResponseToInterventionRecordAggregate;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.ResponseToInterventionLevel;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.ResponseToInterventionStatus;

public class ResponseToInterventionRecordMapper {
    public ResponseToInterventionRecordAggregate from(
            EnterResponseToInterventionRecordCommand enterResponseToInterventionRecordCommand) {
        return new ResponseToInterventionRecordAggregate(
                enterResponseToInterventionRecordCommand.teacherId(),
                enterResponseToInterventionRecordCommand.studentId(),
                enterResponseToInterventionRecordCommand.schoolClassId(),
                new ResponseToInterventionLevel(enterResponseToInterventionRecordCommand.level()),
                new ResponseToInterventionStatus(enterResponseToInterventionRecordCommand.status()));
    }
}
