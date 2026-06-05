package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

public record QuarterReference(String quarterReference) {
    public QuarterReference {
        if (quarterReference == null || quarterReference.isBlank()) {
            throw new IllegalArgumentException("Quarter reference is required");
        }
        if (!quarterReference.equals("FIRST") && !quarterReference.equals("SECOND") && !quarterReference.equals("THIRD")
                && !quarterReference.equals("FOURTH")) {
            throw new IllegalArgumentException("Invalid quarter reference");
        }
    }
}
