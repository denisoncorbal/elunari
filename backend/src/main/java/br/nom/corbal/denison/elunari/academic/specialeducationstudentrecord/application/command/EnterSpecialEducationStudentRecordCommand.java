package br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnterSpecialEducationStudentRecordCommand(
        UUID teacherId,
        UUID studentId,
        UUID schoolClassId,
        String information,
        String status) implements BaseCommand {

}
