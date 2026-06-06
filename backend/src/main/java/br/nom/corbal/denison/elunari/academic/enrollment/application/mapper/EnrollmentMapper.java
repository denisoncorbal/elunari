package br.nom.corbal.denison.elunari.academic.enrollment.application.mapper;

import br.nom.corbal.denison.elunari.academic.enrollment.application.command.EnrollStudentCommand;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.model.aggregate.EnrollmentAggregate;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.model.valueobject.EnrollmentStatus;

public class EnrollmentMapper {
    public EnrollmentAggregate from(EnrollStudentCommand enrollStudentCommand) {
        return new EnrollmentAggregate(
                enrollStudentCommand.studentId(),
                enrollStudentCommand.schoolClassId(),
                new EnrollmentStatus(enrollStudentCommand.status()));
    }
}
