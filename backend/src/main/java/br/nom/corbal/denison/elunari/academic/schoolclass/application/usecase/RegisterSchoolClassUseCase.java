package br.nom.corbal.denison.elunari.academic.schoolclass.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.schoolclass.application.command.RegisterSchoolClassCommand;
import br.nom.corbal.denison.elunari.academic.schoolclass.application.event.SchoolClassEventPublisher;
import br.nom.corbal.denison.elunari.academic.schoolclass.application.mapper.SchoolClassMapper;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.event.SchoolClassRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.entity.SchoolClassEntity;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.model.valueobject.SchoolClassName;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterSchoolClassUseCase implements BaseUseCase<UUID, RegisterSchoolClassCommand> {
    private final SchoolClassRepository schoolClassRepository;

    private final SchoolClassEventPublisher<SchoolClassRegisteredEvent> schoolClassEventPublisher;

    public RegisterSchoolClassUseCase(SchoolClassRepository schoolClassRepository,
            SchoolClassEventPublisher<SchoolClassRegisteredEvent> schoolClassEventPublisher) {
        this.schoolClassEventPublisher = schoolClassEventPublisher;
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public UUID execute(RegisterSchoolClassCommand registerSchoolClassCommand) {
        if (this.schoolClassRepository.existsByName(new SchoolClassName(registerSchoolClassCommand.name()))) {
            throw new IllegalArgumentException("Invalid schoolClass, name must be unique");
        }

        SchoolClassEntity schoolClass = SchoolClassMapper.toSchoolClassEntity(registerSchoolClassCommand);

        this.schoolClassRepository.save(schoolClass);
        this.schoolClassEventPublisher.publish(new SchoolClassRegisteredEvent(schoolClass.getId()));
        return schoolClass.getId();
    }
}
