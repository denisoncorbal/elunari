package br.nom.corbal.denison.elunari.academic.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnrollStudentCommand(
        UUID studentId,
        UUID schoolClassId,
        String status) implements BaseCommand {

}
