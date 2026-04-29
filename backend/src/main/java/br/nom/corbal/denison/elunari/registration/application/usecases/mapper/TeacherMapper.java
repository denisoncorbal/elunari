package br.nom.corbal.denison.elunari.registration.application.usecases.mapper;

import java.util.stream.Collectors;

import br.nom.corbal.denison.elunari.registration.application.usecases.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.domain.Teacher;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Address;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Email;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Gender;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.TeacherStatus;

public class TeacherMapper {
    public Teacher from(RegisterTeacherCommand registerTeacherCommand) {
        return new Teacher(
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
                registerTeacherCommand.phones().stream().map(PhoneNumber::new).collect(Collectors.toSet()),
                registerTeacherCommand.emails().stream().map(Email::new).collect(Collectors.toSet()),
                new TeacherStatus(registerTeacherCommand.teacherStatus()));
    }
}
