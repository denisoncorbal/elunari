package br.nom.corbal.denison.elunari.academic.specialeducationstudentrecord.domain.model.valueobject;

public record SpecialEducationStudentStatus(String status) {
    public SpecialEducationStudentStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
