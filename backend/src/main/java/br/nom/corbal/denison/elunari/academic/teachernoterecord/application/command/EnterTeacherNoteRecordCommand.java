package br.nom.corbal.denison.elunari.academic.teachernoterecord.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnterTeacherNoteRecordCommand(
        UUID teacherId,
        UUID studentId,
        UUID schoolClassId,
        String data,
        String status) implements BaseCommand {

}
