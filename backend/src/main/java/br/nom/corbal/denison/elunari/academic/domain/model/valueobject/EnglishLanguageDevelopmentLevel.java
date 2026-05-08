package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import java.util.List;

public record EnglishLanguageDevelopmentLevel(String level) {
    public EnglishLanguageDevelopmentLevel {
        if (!List.of(
                "LEVEL 1",
                "LEVEL 2",
                "LEVEL 3",
                "LEVEL 4").contains(level)) {
            throw new IllegalArgumentException("Invalid level");
        }
    }
}
