package br.nom.corbal.denison.elunari.academic.honorrollrecord.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnterHonorRollRecordCommand(
        UUID teacherId,
        UUID studentId,
        UUID schoolClassId,
        String level) implements BaseCommand {

}
