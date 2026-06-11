package br.nom.corbal.denison.elunari.academic.subject.application.mapper;

import br.nom.corbal.denison.elunari.academic.subject.application.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.subject.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.subject.domain.model.valueobject.SubjectName;
import br.nom.corbal.denison.elunari.academic.subject.domain.model.valueobject.SubjectStatus;

public final class SubjectMapper {
    private SubjectMapper() {
        throw new AssertionError("Illegal instantiation");
    }

    public static SubjectEntity toSubjectEntity(RegisterSubjectCommand registerSubjectCommand) {
        return new SubjectEntity(
                new SubjectName(registerSubjectCommand.name()),
                new SubjectStatus(registerSubjectCommand.status()));
    }
}
