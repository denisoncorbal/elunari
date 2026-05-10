package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import java.util.List;

public record ResponseToInterventionLevel(String status) {
    public ResponseToInterventionLevel {
        if (!List.of(
                "TIER 1",
                "TIER 2",
                "TIER 3").contains(status)) {
            throw new IllegalArgumentException();
        }
    }
}
