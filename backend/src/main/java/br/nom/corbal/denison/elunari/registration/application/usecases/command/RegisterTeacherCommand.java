package br.nom.corbal.denison.elunari.registration.application.usecases.command;

import java.time.LocalDate;
import java.util.Set;

import br.nom.corbal.denison.elunari.shared.application.usecase.BaseCommand;

public record RegisterTeacherCommand(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String gender,
        String nationality,
        String cpf,
        String federalUnity,
        String locale,
        String neighbourhood,
        String street,
        String zipCode,
        String number,
        Set<String> phones,
        Set<String> emails,
        String teacherStatus) implements BaseCommand {
}
