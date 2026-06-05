package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

public record AssignementName(String name) {
    public AssignementName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }
}
