package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

public record PhoneNumber(String number) {
    public PhoneNumber {
        if (!number.matches("^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$")) {
            throw new IllegalArgumentException("Phone is in an incorrect pattern");
        }
    }
}
