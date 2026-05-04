package br.nom.corbal.denison.elunari.academic.application.command;

import java.time.LocalTime;
import java.util.UUID;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record AllocateTeacherCommand(
        UUID teacherId,
        UUID subjectId,
        UUID schoolClassId,
        LocalTime startTime,
        LocalTime endTime) implements BaseCommand {

}
