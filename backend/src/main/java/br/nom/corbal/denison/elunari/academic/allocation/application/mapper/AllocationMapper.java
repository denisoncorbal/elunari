package br.nom.corbal.denison.elunari.academic.allocation.application.mapper;

import br.nom.corbal.denison.elunari.academic.allocation.application.command.AllocateTeacherCommand;
import br.nom.corbal.denison.elunari.academic.allocation.domain.model.aggregate.AllocationAggregate;
import br.nom.corbal.denison.elunari.academic.allocation.domain.model.valueobject.TimePeriod;

public final class AllocationMapper {
    private AllocationMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static AllocationAggregate toAllocationAggregate(AllocateTeacherCommand allocateTeacherCommand) {
        return new AllocationAggregate(
                allocateTeacherCommand.teacherId(),
                allocateTeacherCommand.subjectId(),
                allocateTeacherCommand.schoolClassId(),
                new TimePeriod(allocateTeacherCommand.startTime(), allocateTeacherCommand.endTime()));
    }
}
