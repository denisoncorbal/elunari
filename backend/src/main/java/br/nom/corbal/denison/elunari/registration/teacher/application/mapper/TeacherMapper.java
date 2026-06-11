package br.nom.corbal.denison.elunari.registration.teacher.application.mapper;

import java.util.stream.Collectors;

import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Address;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Cpf;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Email;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Gender;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Nationality;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.PersonName;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.teacher.application.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.teacher.domain.model.entity.TeacherEntity;
import br.nom.corbal.denison.elunari.registration.teacher.domain.model.valueobject.TeacherStatus;

public final class TeacherMapper {
        private TeacherMapper() {
                throw new AssertionError("Illegal instantiation");
        }

        public static TeacherEntity toTeacherEntity(RegisterTeacherCommand registerTeacherCommand) {
                return new TeacherEntity(
                                new PersonName(
                                                registerTeacherCommand.firstName(),
                                                registerTeacherCommand.lastName()),
                                registerTeacherCommand.dateOfBirth(),
                                new Gender(registerTeacherCommand.gender()),
                                new Nationality(registerTeacherCommand.nationality()),
                                new Cpf(registerTeacherCommand.cpf()),
                                new Address(
                                                registerTeacherCommand.federalUnity(),
                                                registerTeacherCommand.locale(),
                                                registerTeacherCommand.neighbourhood(),
                                                registerTeacherCommand.street(),
                                                registerTeacherCommand.zipCode(),
                                                registerTeacherCommand.number()),
                                registerTeacherCommand.phones().stream().map(PhoneNumber::new)
                                                .collect(Collectors.toSet()),
                                registerTeacherCommand.emails().stream().map(Email::new).collect(Collectors.toSet()),
                                new TeacherStatus(registerTeacherCommand.teacherStatus()));
        }
}
