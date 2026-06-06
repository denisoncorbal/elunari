package br.nom.corbal.denison.elunari.academic.allocation.domain.repository;

import java.util.Set;
import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.model.aggregate.AllocationAggregate;

public interface AllocationRepository {
    public Set<AllocationAggregate> findAllByTeacherIdAndStatusActive(UUID teacherId);

    public AllocationAggregate save(AllocationAggregate allocationAggregate);

    public boolean isTeacherAllocatedToSchoolClass(UUID teacherId, UUID schoolClassId);

    public boolean isTeacherAllocatedToSchoolClassAndSubject(UUID teacherId, UUID schoolClassId, UUID subjectId);
}
