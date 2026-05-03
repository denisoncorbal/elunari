package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

public record SchoolClassStatus(String status) {
    public SchoolClassStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid status");
        }
    }

}
