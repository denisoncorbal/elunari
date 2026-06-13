package br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnterReadingLevelRecordCommand(
        UUID teacherId,
        UUID studentId,
        UUID schoolClassId,
        String data) implements BaseCommand {

}
