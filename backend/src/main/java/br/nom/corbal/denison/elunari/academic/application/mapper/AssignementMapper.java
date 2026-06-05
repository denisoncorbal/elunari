package br.nom.corbal.denison.elunari.academic.application.mapper;

import br.nom.corbal.denison.elunari.academic.application.command.RegisterAssignementCommand;
import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AssignementAggregate;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementGrade;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementName;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementStatus;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementWeight;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.QuarterReference;

public class AssignementMapper {
    public AssignementAggregate from(RegisterAssignementCommand registerAssignementCommand) {
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
