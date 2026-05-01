package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

import java.util.List;

public record Address(
        String federalUnity,
        String locale,
        String neighbourhood,
        String street,
        String zipCode,
        String number) {
    public Address {
        final List<String> validFederalUnities = List.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
                "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR",
                "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC",
                "SP", "SE", "TO");
        if (!validFederalUnities.contains(federalUnity)) {
            throw new IllegalArgumentException("Invalid Federal Unity");
        }
        if (!zipCode.matches("^(?:\\d{8}|\\d{2}\\.\\d{3}-\\d{3})$")) {
            throw new IllegalArgumentException("Invalid zip code");
        }
        if (locale == null || locale.isBlank()) {
            throw new IllegalArgumentException("Locale is required");
        }
        if (neighbourhood == null || neighbourhood.isBlank()) {
            throw new IllegalArgumentException("Neighbourhood is required");
        }
    }
}
