package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.command;

import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record EnterEnglishLanguageDevelopmentRecordCommand(
                UUID teacherId,
                UUID studentId,
                UUID schoolClassId,
                String level) implements BaseCommand {

}
