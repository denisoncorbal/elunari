package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

public record Email(String email) {
    public Email {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}
