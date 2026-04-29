package br.nom.corbal.denison.elunari.registration.application.usecases;

import java.util.stream.Collectors;

import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.domain.Student;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Address;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Email;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Gender;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.GuardianInfo;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.GuardianType;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.StudentStatus;

public class RegisterStudentMapper {
    public Student from(RegisterStudentCommand registerStudentCommand) {
        return new Student(
                new PersonName(
                        registerStudentCommand.firstName(),
                        registerStudentCommand.lastName()),
                registerStudentCommand.dateOfBirth(),
                registerStudentCommand.guardians().stream()
                        .map(
                                g -> new GuardianInfo(
                                        new PersonName(g.firstName(), g.lastName()),
                                        g.phones().stream().map(PhoneNumber::new).collect(Collectors.toSet()),
                                        g.emails().stream().map(Email::new).collect(Collectors.toSet()),
                                        new GuardianType(g.guardianType())))
                        .collect(Collectors.toSet()),
                new Gender(registerStudentCommand.gender()),
                new Nationality(registerStudentCommand.nationality()),
                new Cpf(registerStudentCommand.cpf()),
                new Address(
                        registerStudentCommand.federalUnity(),
                        registerStudentCommand.locale(),
                        registerStudentCommand.neighbourhood(),
                        registerStudentCommand.street(),
                        registerStudentCommand.zipCode(),
                        registerStudentCommand.number()),
                new StudentStatus(registerStudentCommand.studentStatus()));
    }
}
