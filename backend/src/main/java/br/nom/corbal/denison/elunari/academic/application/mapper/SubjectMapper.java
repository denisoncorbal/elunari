package br.nom.corbal.denison.elunari.academic.application.mapper;

import br.nom.corbal.denison.elunari.academic.application.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.domain.model.entity.SubjectEntity;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectName;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.SubjectStatus;

public class SubjectMapper {
    public SubjectEntity from(RegisterSubjectCommand registerSubjectCommand) {
        return new SubjectEntity(
                new SubjectName(registerSubjectCommand.name()),
                new SubjectStatus(registerSubjectCommand.status()));
    }
}
