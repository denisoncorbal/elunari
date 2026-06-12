package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject;

public record TeacherNoteStatus(String status) {
    public TeacherNoteStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
