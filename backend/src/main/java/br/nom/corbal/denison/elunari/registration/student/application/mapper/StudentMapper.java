package br.nom.corbal.denison.elunari.registration.student.application.mapper;

import java.util.stream.Collectors;

import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Address;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Cpf;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Email;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Gender;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.GuardianInfo;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.GuardianType;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Nationality;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.PersonName;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.student.application.command.RegisterStudentCommand;
import br.nom.corbal.denison.elunari.registration.student.domain.model.entity.StudentEntity;
import br.nom.corbal.denison.elunari.registration.student.domain.model.valueobject.StudentStatus;

public final class StudentMapper {
        private StudentMapper() {
                throw new AssertionError("Illegal instantiation");
        }

        public static StudentEntity toStudentEntity(RegisterStudentCommand registerStudentCommand) {
                return new StudentEntity(
                                new PersonName(
                                                registerStudentCommand.firstName(),
                                                registerStudentCommand.lastName()),
                                registerStudentCommand.dateOfBirth(),
                                registerStudentCommand.guardians().stream()
                                                .map(
                                                                g -> new GuardianInfo(
                                                                                new PersonName(g.firstName(),
                                                                                                g.lastName()),
                                                                                g.phones().stream()
                                                                                                .map(PhoneNumber::new)
                                                                                                .collect(Collectors
                                                                                                                .toSet()),
                                                                                g.emails().stream().map(Email::new)
                                                                                                .collect(Collectors
                                                                                                                .toSet()),
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
