package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import java.util.List;

public record Grade(String grade) {
    public Grade {
        if (grade == null || grade.isBlank()) {
            throw new IllegalArgumentException("Grade is required");
        }
        if (!List.of(
                "PRE-K 2",
                "PRE-K 3",
                "PRE-K 4",
                "KINDERGARTEN",
                "GRADE 1",
                "GRADE 2",
                "GRADE 3",
                "GRADE 4",
                "GRADE 5",
                "GRADE 6",
                "GRADE 7",
                "GRADE 8",
                "GRADE 9",
                "GRADE 10",
                "GRADE 11",
                "GRADE 12").contains(grade)) {
            throw new IllegalArgumentException("Invalid grade");
        }
    }

}
