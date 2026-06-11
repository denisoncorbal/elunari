package br.nom.corbal.denison.elunari.academic.assignement.application.mapper;

import br.nom.corbal.denison.elunari.academic.assignement.application.command.RegisterAssignementCommand;
import br.nom.corbal.denison.elunari.academic.assignement.domain.model.aggregate.AssignementAggregate;
import br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject.AssignementGrade;
import br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject.AssignementName;
import br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject.AssignementStatus;
import br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject.AssignementWeight;
import br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject.QuarterReference;

public final class AssignementMapper {

    private AssignementMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static AssignementAggregate toAssignementAggregate(RegisterAssignementCommand registerAssignementCommand) {
        return new AssignementAggregate(registerAssignementCommand.teacherId(),
                registerAssignementCommand.schoolClassId(), registerAssignementCommand.subjectId(),
                registerAssignementCommand.studentId(),
                new QuarterReference(registerAssignementCommand.quarterReference()),
                new AssignementName(registerAssignementCommand.name()),
                new AssignementWeight(registerAssignementCommand.weight()),
                new AssignementGrade(registerAssignementCommand.grade()),
                new AssignementStatus(registerAssignementCommand.status()));
    }
}
