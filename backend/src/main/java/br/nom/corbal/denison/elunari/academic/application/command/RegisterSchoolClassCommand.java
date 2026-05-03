package br.nom.corbal.denison.elunari.academic.application.command;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record RegisterSchoolClassCommand(
                String name,
                String grade,
                int referenceYear,
                String status) implements BaseCommand {

}
