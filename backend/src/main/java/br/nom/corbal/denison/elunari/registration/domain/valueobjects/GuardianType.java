package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

public record GuardianType(String guardianType) {
    public GuardianType {
        if (guardianType == null || guardianType.isBlank()) {
            throw new IllegalArgumentException("Guardian type is required");
        }
        if (!guardianType.equals("FATHER") && !guardianType.equals("MOTHER") && !guardianType.equals("OTHER")) {
            throw new IllegalArgumentException("Invalid guardian type");
        }
    }
}
