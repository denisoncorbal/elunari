package br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.command.EnterReadingLevelRecordCommand;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.event.ReadingLevelRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.application.mapper.ReadingLevelRecordMapper;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.event.ReadingLevelRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.model.aggregate.ReadingLevelRecordAggregate;
import br.nom.corbal.denison.elunari.academic.readinglevelrecord.domain.repository.ReadingLevelRecordRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnterReadingLevelRecordUseCase implements BaseUseCase<UUID, EnterReadingLevelRecordCommand> {
    private final TeacherGateway teacherGateway;

    private final StudentGateway studentGateway;

    private final SchoolClassRepository schoolClassRepository;

    private final AllocationRepository allocationRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final ReadingLevelRecordRepository readingLevelRecordRepository;

    private final ReadingLevelRecordEventPublisher<ReadingLevelRecordEnteredEvent> readingLevelRecordEventPublisher;

    public EnterReadingLevelRecordUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, AllocationRepository allocationRepository,
            EnrollmentRepository enrollmentRepository,
            ReadingLevelRecordRepository readingLevelRecordRepository,
            ReadingLevelRecordEventPublisher<ReadingLevelRecordEnteredEvent> readingLevelRecordEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.readingLevelRecordRepository = readingLevelRecordRepository;
        this.readingLevelRecordEventPublisher = readingLevelRecordEventPublisher;
    }

    @Override
    public UUID execute(EnterReadingLevelRecordCommand enterReadingLevelRecordCommand) {
        if (!this.teacherGateway.existsById(enterReadingLevelRecordCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }
        if (!this.studentGateway.existsById(enterReadingLevelRecordCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }
        if (!this.schoolClassRepository.existsById(enterReadingLevelRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }
        if (!this.allocationRepository.isTeacherAllocatedToSchoolClass(
                enterReadingLevelRecordCommand.teacherId(),
                enterReadingLevelRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class");
        }
        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                enterReadingLevelRecordCommand.studentId(),
                enterReadingLevelRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        ReadingLevelRecordAggregate readingLevelRecordAggregate = ReadingLevelRecordMapper
                .toReadingLevelRecordAggregate(enterReadingLevelRecordCommand);

        this.readingLevelRecordRepository.save(readingLevelRecordAggregate);

        this.readingLevelRecordEventPublisher.publish(
                new ReadingLevelRecordEnteredEvent(readingLevelRecordAggregate.getId()));

        return readingLevelRecordAggregate.getId();
    }
}
