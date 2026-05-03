package br.nom.corbal.denison.elunari.academic.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.application.command.RegisterSchoolClassCommand;
import br.nom.corbal.denison.elunari.academic.application.event.schoolclass.SchoolClassEventPublisher;
import br.nom.corbal.denison.elunari.academic.application.mapper.SchoolClassMapper;
import br.nom.corbal.denison.elunari.academic.domain.event.SchoolClassRegisteredEvent;
import br.nom.corbal.denison.elunari.academic.domain.model.entity.SchoolClassEntity;
import br.nom.corbal.denison.elunari.academic.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class RegisterSchoolClassUseCase implements BaseUseCase<UUID, RegisterSchoolClassCommand> {
    private final SchoolClassRepository schoolClassRepository;

    private final SchoolClassEventPublisher<SchoolClassRegisteredEvent> schoolClassEventPublisher;

    private final SchoolClassMapper registerSchoolClassMapper = new SchoolClassMapper();

    public RegisterSchoolClassUseCase(SchoolClassRepository schoolClassRepository,
            SchoolClassEventPublisher<SchoolClassRegisteredEvent> schoolClassEventPublisher) {
        this.schoolClassEventPublisher = schoolClassEventPublisher;
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public UUID execute(RegisterSchoolClassCommand registerSchoolClassCommand) {
        SchoolClassEntity schoolClass = this.registerSchoolClassMapper.from(registerSchoolClassCommand);
        if (this.schoolClassRepository.existsByName(schoolClass.getName())) {
            throw new IllegalArgumentException("Invalid schoolClass, name must be unique");
        }
        this.schoolClassRepository.save(schoolClass);
        this.schoolClassEventPublisher.publish(new SchoolClassRegisteredEvent(schoolClass.getId()));
        return schoolClass.getId();
    }
}
