package br.nom.corbal.denison.elunari.academic.domain.valueobjects;

public record SubjectStatus(String status) {
    public SubjectStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Subject status is required");
        }
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid subject status");
        }
    }
}
