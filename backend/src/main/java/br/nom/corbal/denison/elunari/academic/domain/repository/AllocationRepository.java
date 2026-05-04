package br.nom.corbal.denison.elunari.academic.domain.repository;

import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AllocationAggregate;

public interface AllocationRepository {
    public Set<AllocationAggregate> findAllByTeacherIdAndStatusActive(UUID teacherId);

    public AllocationAggregate save(AllocationAggregate allocationAggregate);
}
