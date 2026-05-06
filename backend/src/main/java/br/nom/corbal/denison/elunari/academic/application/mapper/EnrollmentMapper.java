package br.nom.corbal.denison.elunari.academic.application.mapper;

import br.nom.corbal.denison.elunari.academic.application.command.EnrollStudentCommand;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.EnrollmentAggregate;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.EnrollmentStatus;

public class EnrollmentMapper {
    public EnrollmentAggregate from(EnrollStudentCommand enrollStudentCommand) {
        return new EnrollmentAggregate(
                enrollStudentCommand.studentId(),
                enrollStudentCommand.schoolClassId(),
                new EnrollmentStatus(enrollStudentCommand.status()));
    }
}
