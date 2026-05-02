package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

public record Gender(String gender) {
    public Gender {
        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("Gender is required");
        }
        if (gender.length() > 1 || (!gender.equals("M") && !gender.equals("F"))) {
            throw new IllegalArgumentException("Invalid gender");
        }
    }
}
