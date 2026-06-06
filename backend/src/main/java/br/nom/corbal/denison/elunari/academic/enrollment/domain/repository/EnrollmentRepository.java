package br.nom.corbal.denison.elunari.academic.enrollment.domain.repository;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.enrollment.domain.model.aggregate.EnrollmentAggregate;

public interface EnrollmentRepository {
    public EnrollmentAggregate save(EnrollmentAggregate enrollmentAggregate);

    public boolean isStudentAlreadyEnrolled(UUID studentId);

    public boolean isStudentEnrolledToSchoolClass(UUID studentId, UUID schoolClassId);
}
