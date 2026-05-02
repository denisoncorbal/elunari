package br.nom.corbal.denison.elunari.registration.application.mapper;

import java.util.stream.Collectors;

import br.nom.corbal.denison.elunari.registration.application.command.RegisterTeacherCommand;
import br.nom.corbal.denison.elunari.registration.domain.model.entity.TeacherEntity;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Address;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Email;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Gender;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.TeacherStatus;

public class TeacherMapper {
    public TeacherEntity from(RegisterTeacherCommand registerTeacherCommand) {
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
                registerTeacherCommand.phones().stream().map(PhoneNumber::new).collect(Collectors.toSet()),
                registerTeacherCommand.emails().stream().map(Email::new).collect(Collectors.toSet()),
                new TeacherStatus(registerTeacherCommand.teacherStatus()));
    }
}
