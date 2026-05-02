package br.nom.corbal.denison.elunari.registration.domain.model.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Address;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Cpf;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Email;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Gender;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.GuardianInfo;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Nationality;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PersonName;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.PhoneNumber;
import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.StudentStatus;

public class StudentEntity {
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

    public StudentEntity(PersonName personName, LocalDate dateOfBirth, Set<GuardianInfo> guardians, Gender gender,
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
