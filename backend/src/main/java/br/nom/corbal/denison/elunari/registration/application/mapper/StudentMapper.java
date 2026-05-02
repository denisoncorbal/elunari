package br.nom.corbal.denison.elunari.registration.application.mapper;

import java.util.stream.Collectors;

import br.nom.corbal.denison.elunari.registration.application.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.domain.model.entity.StudentEntity;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Address;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Email;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Gender;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.GuardianInfo;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.GuardianType;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.StudentStatus;

public class StudentMapper {
    public StudentEntity from(RegisterStudentCommand registerStudentCommand) {
        return new StudentEntity(
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
