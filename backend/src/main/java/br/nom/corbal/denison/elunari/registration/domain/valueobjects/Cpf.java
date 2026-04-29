package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

public record Cpf(String cpf) {
    public Cpf {
        if (!cpf.matches("^(?:\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$")) {
            throw new IllegalArgumentException("Invalid CPF");
        }
    }
}
