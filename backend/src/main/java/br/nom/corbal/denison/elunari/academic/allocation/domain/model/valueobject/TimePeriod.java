package br.nom.corbal.denison.elunari.academic.allocation.domain.model.valueobject;

import java.time.LocalTime;

public record TimePeriod(LocalTime start, LocalTime end) {
    public TimePeriod {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Time period is required");
        }
        if (start.equals(end) || start.isAfter(end)) {
            throw new IllegalArgumentException("Invalid time period");
        }
    }
}
