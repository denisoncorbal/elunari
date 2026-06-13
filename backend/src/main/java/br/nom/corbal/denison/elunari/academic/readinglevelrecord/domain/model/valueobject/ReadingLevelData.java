package br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.valueobject;

public record ReadingLevelData(String data) {
    public ReadingLevelData {
        if (!data.matches("^[a-zA-Z0-9]{2}$")) {
            throw new IllegalArgumentException("Invalid data");
        }
    }
}
