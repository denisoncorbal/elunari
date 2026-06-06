package br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject;

public record AssignementWeight(int weight) {
    private final static int ASSIGNEMENT_WEIGHT_MINIMUM_VALUE = 0;
    private final static int ASSIGNEMENT_WEIGHT_MAXIMUM_VALUE = 10;

    public AssignementWeight {
        if (weight < ASSIGNEMENT_WEIGHT_MINIMUM_VALUE || weight > ASSIGNEMENT_WEIGHT_MAXIMUM_VALUE) {
            throw new IllegalArgumentException("Invalid weight");
        }
    }
}
