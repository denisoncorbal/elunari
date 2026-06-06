package br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject;

public record AssignementStatus(String status) {
    public AssignementStatus{
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
        if (!status.equals("ACTIVE")
            && !status.equals("GRADED")
            && !status.equals("INACTIVE")
        ) {
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
