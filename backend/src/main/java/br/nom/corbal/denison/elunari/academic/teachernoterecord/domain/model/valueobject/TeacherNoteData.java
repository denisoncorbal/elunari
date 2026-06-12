package br.nom.corbal.denison.elunari.academic.teachernoterecord.domain.model.valueobject;

public record TeacherNoteData(String data) {
    public TeacherNoteData {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Data is required");
        }
    }
}
