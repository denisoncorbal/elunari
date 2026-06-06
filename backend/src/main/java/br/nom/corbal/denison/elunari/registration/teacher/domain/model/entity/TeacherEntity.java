package br.nom.corbal.denison.elunari.registration.teacher.domain.model.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Address;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Cpf;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Email;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Gender;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.Nationality;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.PersonName;
import br.nom.corbal.denison.elunari.registration.shared.domain.model.valueobject.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.teacher.domain.model.valueobject.TeacherStatus;

public class TeacherEntity {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private PersonName personName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Nationality nationality;
    private Cpf cpf;
    private Address address;
    private Set<PhoneNumber> phones;
    private Set<Email> emails;
    private TeacherStatus status;

    public TeacherEntity(PersonName personName, LocalDate dateOfBirth, Gender gender, Nationality nationality,
            Cpf cpf, Address address, Set<PhoneNumber> phones, Set<Email> emails, TeacherStatus status) {
        this.id = UUID.randomUUID();
        this.personName = personName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.cpf = cpf;
        this.address = address;
        this.phones = phones;
        this.emails = emails;
        this.status = status;
    }
}
