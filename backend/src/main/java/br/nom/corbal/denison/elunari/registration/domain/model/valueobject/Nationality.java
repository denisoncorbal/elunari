package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

public record Nationality(String nationality) {
    public Nationality {
        if (nationality == null || nationality.isBlank()) {
            throw new IllegalArgumentException("Invalid nationality");
        }
    }
}
