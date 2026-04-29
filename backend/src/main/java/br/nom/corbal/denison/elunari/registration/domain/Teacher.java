package br.nom.corbal.denison.elunari.registration.domain;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Address;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Email;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Gender;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.TeacherStatus;

public class Teacher {
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

    public Teacher(PersonName personName, LocalDate dateOfBirth, Gender gender, Nationality nationality,
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
