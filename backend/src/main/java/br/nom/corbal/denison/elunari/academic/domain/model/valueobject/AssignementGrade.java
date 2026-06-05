package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

public record AssignementGrade(double grade) {
    private static final double ASSIGNEMENT_GRADE_MINIMUM_VALUE = 0.0;
    private static final double ASSIGNEMENT_GRADE_MAXIMUM_VALUE = 100.0;

    public AssignementGrade {
        if (grade < ASSIGNEMENT_GRADE_MINIMUM_VALUE || grade > ASSIGNEMENT_GRADE_MAXIMUM_VALUE) {
            throw new IllegalArgumentException("Invalid grade");
        }
    }
}
