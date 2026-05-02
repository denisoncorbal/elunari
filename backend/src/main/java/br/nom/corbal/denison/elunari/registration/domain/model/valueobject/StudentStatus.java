package br.nom.corbal.denison.elunari.registration.domain.model.valueobject;

public record StudentStatus(String status) {
    public StudentStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Student status is required");
        }
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid student status");
        }
    }
}
