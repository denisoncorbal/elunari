package br.nom.corbal.denison.elunari.academic.application.usecases.mapper;

import br.nom.corbal.denison.elunari.academic.application.usecases.command.RegisterSubjectCommand;
import br.nom.corbal.denison.elunari.academic.domain.Subject;
import br.nom.corbal.denison.elunari.academic.domain.valueobjects.SubjectName;
import br.nom.corbal.denison.elunari.academic.domain.valueobjects.SubjectStatus;

public class SubjectMapper {
    public Subject from(RegisterSubjectCommand registerSubjectCommand) {
        return new Subject(
                new SubjectName(registerSubjectCommand.name()),
                new SubjectStatus(registerSubjectCommand.status()));
    }
}
