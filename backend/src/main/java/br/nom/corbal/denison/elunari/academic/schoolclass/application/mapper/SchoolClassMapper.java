package br.nom.corbal.denison.elunari.academic.schoolclass.application.mapper;

import br.nom.corbal.denison.elunari.academic.schoolclass.application.command.RegisterSchoolClassCommand;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.entity.SchoolClassEntity;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject.Grade;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject.SchoolClassName;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject.SchoolClassReferenceYear;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject.SchoolClassStatus;

public class SchoolClassMapper {
    public SchoolClassEntity from(RegisterSchoolClassCommand registerSchoolClassCommand) {
        return new SchoolClassEntity(
                new SchoolClassName(registerSchoolClassCommand.name()),
                new Grade(registerSchoolClassCommand.grade()),
                new SchoolClassReferenceYear(registerSchoolClassCommand.referenceYear()),
                new SchoolClassStatus(registerSchoolClassCommand.status()));
    }
}
