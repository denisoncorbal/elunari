package br.nom.corbal.denison.elunari.academic.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnterResponseToInterventionRecordCommand(
        UUID teacherId,
        UUID studentId,
        UUID schoolClassId,
        String level) implements BaseCommand {

}
