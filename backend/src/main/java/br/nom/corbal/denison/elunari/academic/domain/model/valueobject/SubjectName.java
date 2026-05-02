package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

public record SubjectName(String name) {
    public SubjectName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Subject name is required");
        }
    }
}
