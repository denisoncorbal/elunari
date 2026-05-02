package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

import java.util.Set;

public record GuardianInfo(PersonName name, Set<PhoneNumber> phones, Set<Email> emails, GuardianType guardianType) {

}
