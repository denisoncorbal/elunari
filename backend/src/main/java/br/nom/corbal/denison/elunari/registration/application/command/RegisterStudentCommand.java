package br.nom.corbal.denison.elunari.registration.application.command;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import br.nom.corbal.denison.elunari.shared.application.cqrs.BaseCommand;

public record RegisterStudentCommand(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        List<GuardianDetails> guardians,
        String gender,
        String nationality,
        String cpf,
        String federalUnity,
        String locale,
        String neighbourhood,
        String street,
        String zipCode,
        String number,
        String studentStatus) implements BaseCommand {
    public record GuardianDetails(
            String firstName,
            String lastName,
            Set<String> phones,
            Set<String> emails,
            String guardianType) {
    }

}