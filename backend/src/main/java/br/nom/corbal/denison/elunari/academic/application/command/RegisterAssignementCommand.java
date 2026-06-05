package br.nom.corbal.denison.elunari.academic.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record RegisterAssignementCommand(
        UUID teacherId,
        UUID schoolClassId,
        UUID subjectId,
        UUID studentId,
        String quarterReference,
        String name,
        int weight,
        double grade,
        String status) implements BaseCommand {

}
