package br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject;

public record SchoolClassName(String name) {
    public SchoolClassName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("School class name is required");
        }
    }
}
