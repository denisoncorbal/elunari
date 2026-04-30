package br.nom.corbal.denison.elunari.academic.application.usecases.command;

import br.nom.corbal.denison.elunari.shared.application.usecase.BaseCommand;

public record RegisterSubjectCommand(String name, String status) implements BaseCommand {
}
