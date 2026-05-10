package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

public record ResponseToInterventionStatus(String status) {
    public ResponseToInterventionStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
