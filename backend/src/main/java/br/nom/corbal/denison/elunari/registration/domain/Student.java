package br.nom.corbal.denison.elunari.registration.domain;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Address;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Email;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Gender;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.GuardianInfo;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.valueobjects.StudentStatus;

public class Student {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    private PersonName personName;
    private LocalDate dateOfBirth;
    private Set<GuardianInfo> guardians;
    private Gender gender;
    private Nationality nationality;
    private Cpf cpf;
    private Address address;
    private Set<PhoneNumber> phones;
    private Set<Email> emails;
    private StudentStatus status;

    public Student(PersonName personName, LocalDate dateOfBirth, Set<GuardianInfo> guardians, Gender gender,
            Nationality nationality, Cpf cpf, Address address, StudentStatus status) {
        this.id = UUID.randomUUID();
        this.personName = personName;
        this.dateOfBirth = dateOfBirth;
        this.guardians = guardians;
        this.gender = gender;
        this.nationality = nationality;
        this.cpf = cpf;
        this.address = address;
        this.status = status;
    }

}
