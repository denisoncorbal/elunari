package br.nom.corbal.denison.elunari.academic.application.mapper;

import br.nom.corbal.denison.elunari.academic.application.command.AllocateTeacherCommand;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AllocationAggregate;

public class AllocationMapper {
    public AllocationAggregate from(AllocateTeacherCommand allocateTeacherCommand) {
        return new AllocationAggregate(
                allocateTeacherCommand.teacherId(), allocateTeacherCommand.subjectId(),
                allocateTeacherCommand.schoolClassId(), allocateTeacherCommand.startTime(),
                allocateTeacherCommand.endTime());
    }
}
