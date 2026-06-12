package br.nom.corbal.denison.elunari.academic.honorrollrecord.application.mapper;

import br.nom.corbal.denison.elunari.academic.honorrollrecord.application.command.EnterHonorRollRecordCommand;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.aggregate.HonorRollRecordAggregate;
import br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.valueobject.HonorRollLevel;

public final class HonorRollRecordMapper {
    private HonorRollRecordMapper() {
        throw new AssertionError("Forbbiden instatiation");
    }

    public static HonorRollRecordAggregate toHonorRollRecordAggregate(
            EnterHonorRollRecordCommand enterHonorRollRecordCommand) {
        return new HonorRollRecordAggregate(enterHonorRollRecordCommand.teacherId(),
                enterHonorRollRecordCommand.studentId(), enterHonorRollRecordCommand.schoolClassId(),
                new HonorRollLevel(enterHonorRollRecordCommand.level()));
    }
}
