package br.nom.corbal.denison.elunari.academic.application.command;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record RegisterSubjectCommand(String name, String status) implements BaseCommand {
}
