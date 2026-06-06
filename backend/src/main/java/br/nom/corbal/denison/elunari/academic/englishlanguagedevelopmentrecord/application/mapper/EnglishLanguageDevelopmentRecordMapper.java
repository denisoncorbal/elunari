package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.mapper;

import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.command.EnterEnglishLanguageDevelopmentRecordCommand;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.aggregate.EnglishLanguageDevelopmentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.valueobject.EnglishLanguageDevelopmentLevel;

public class EnglishLanguageDevelopmentRecordMapper {
    public EnglishLanguageDevelopmentRecordAggregate from(
            EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand) {
        return new EnglishLanguageDevelopmentRecordAggregate(
                enterEnglishLanguageDevelopmentRecordCommand.teacherId(),
                enterEnglishLanguageDevelopmentRecordCommand.studentId(),
                enterEnglishLanguageDevelopmentRecordCommand.schoolClassId(),
                new EnglishLanguageDevelopmentLevel(enterEnglishLanguageDevelopmentRecordCommand.level()));
    }
}
