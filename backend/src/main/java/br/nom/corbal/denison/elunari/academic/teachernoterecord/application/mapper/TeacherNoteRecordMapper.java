package br.nom.corbal.denison.elunari.academic.teachernoterecord.application.mapper;

import br.nom.corbal.denison.elunari.academic.teachernoterecord.application.command.EnterTeacherNoteRecordCommand;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.aggregate.TeacherNoteRecordAggregate;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject.TeacherNoteData;
import br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject.TeacherNoteStatus;

public final class TeacherNoteRecordMapper {
    private TeacherNoteRecordMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static TeacherNoteRecordAggregate toTeacherNoteRecordAggregate(
            EnterTeacherNoteRecordCommand enterTeacherNoteRecordCommand) {
        return new TeacherNoteRecordAggregate(enterTeacherNoteRecordCommand.teacherId(),
                enterTeacherNoteRecordCommand.studentId(), enterTeacherNoteRecordCommand.schoolClassId(),
                new TeacherNoteData(enterTeacherNoteRecordCommand.data()),
                new TeacherNoteStatus(enterTeacherNoteRecordCommand.status()));
    }
}
