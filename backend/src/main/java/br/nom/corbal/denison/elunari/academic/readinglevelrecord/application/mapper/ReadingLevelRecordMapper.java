package br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.mapper;

import br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.command.EnterReadingLevelRecordCommand;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.aggregate.ReadingLevelRecordAggregate;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.valueobject.ReadingLevelData;

public final class ReadingLevelRecordMapper {
    private ReadingLevelRecordMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static ReadingLevelRecordAggregate toReadingLevelRecordAggregate(
            EnterReadingLevelRecordCommand enterReadingLevelRecordCommand) {
        return new ReadingLevelRecordAggregate(enterReadingLevelRecordCommand.teacherId(),
                enterReadingLevelRecordCommand.studentId(), enterReadingLevelRecordCommand.studentId(),
                enterReadingLevelRecordCommand.schoolClassId(),
                new ReadingLevelData(enterReadingLevelRecordCommand.data()));
    }
}
