package br.nom.corbal.denison.elunari.academic.honorrollrecord.domain.model.valueobject;

import java.util.List;

public record HonorRollLevel(String level) {
    public HonorRollLevel {
        if (!List.of(
                "HONOR ROLL",
                "HIGH HONOR ROLL").contains(level)) {
            throw new IllegalArgumentException("Invalid level");
        }
    }
}
