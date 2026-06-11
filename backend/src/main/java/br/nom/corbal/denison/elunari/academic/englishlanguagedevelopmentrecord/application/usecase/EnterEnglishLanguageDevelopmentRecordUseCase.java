package br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.usecase;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.allocation.domain.repository.AllocationRepository;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.command.EnterEnglishLanguageDevelopmentRecordCommand;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.event.EnglishLanguageDevelopmentRecordEventPublisher;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.application.mapper.EnglishLanguageDevelopmentRecordMapper;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.event.EnglishLanguageDevelopmentRecordEnteredEvent;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.model.aggregate.EnglishLanguageDevelopmentRecordAggregate;
import br.nom.corbal.denison.elunari.academic.englishlanguagedevelopmentrecord.domain.repository.EnglishLanguageDevelopmentRecordRepository;
import br.nom.corbal.denison.elunari.academic.enrollment.domain.repository.EnrollmentRepository;
import br.nom.corbal.denison.elunari.academic.schoolclass.domain.repository.SchoolClassRepository;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.StudentGateway;
import br.nom.corbal.denison.elunari.academic.shared.domain.gateway.TeacherGateway;
import br.nom.corbal.denison.elunari.shared.application.usecase.BaseUseCase;

public class EnterEnglishLanguageDevelopmentRecordUseCase
        implements BaseUseCase<UUID, EnterEnglishLanguageDevelopmentRecordCommand> {

    private final TeacherGateway teacherGateway;

    private final StudentGateway studentGateway;

    private final SchoolClassRepository schoolClassRepository;

    private final AllocationRepository allocationRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final EnglishLanguageDevelopmentRecordRepository englishLanguageDevelopmentRecordRepository;

    private final EnglishLanguageDevelopmentRecordEventPublisher<EnglishLanguageDevelopmentRecordEnteredEvent> englishLanguageDevelopmentRecordEventPublisher;

    public EnterEnglishLanguageDevelopmentRecordUseCase(TeacherGateway teacherGateway, StudentGateway studentGateway,
            SchoolClassRepository schoolClassRepository, AllocationRepository allocationRepository,
            EnrollmentRepository enrollmentRepository,
            EnglishLanguageDevelopmentRecordRepository englishLanguageDevelopmentRecordRepository,
            EnglishLanguageDevelopmentRecordEventPublisher<EnglishLanguageDevelopmentRecordEnteredEvent> englishLanguageDevelopmentRecordEventPublisher) {
        this.teacherGateway = teacherGateway;
        this.studentGateway = studentGateway;
        this.schoolClassRepository = schoolClassRepository;
        this.allocationRepository = allocationRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.englishLanguageDevelopmentRecordRepository = englishLanguageDevelopmentRecordRepository;
        this.englishLanguageDevelopmentRecordEventPublisher = englishLanguageDevelopmentRecordEventPublisher;
    }

    @Override
    public UUID execute(EnterEnglishLanguageDevelopmentRecordCommand enterEnglishLanguageDevelopmentRecordCommand) {
        if (!this.teacherGateway.existsById(enterEnglishLanguageDevelopmentRecordCommand.teacherId())) {
            throw new IllegalArgumentException("Invalid teacher");
        }
        if (!this.studentGateway.existsById(enterEnglishLanguageDevelopmentRecordCommand.studentId())) {
            throw new IllegalArgumentException("Invalid student");
        }
        if (!this.schoolClassRepository.existsById(enterEnglishLanguageDevelopmentRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Invalid school class");
        }
        if (!this.allocationRepository.isTeacherAllocatedToSchoolClass(
                enterEnglishLanguageDevelopmentRecordCommand.teacherId(),
                enterEnglishLanguageDevelopmentRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Teacher is not allocated to the school class");
        }
        if (!this.enrollmentRepository.isStudentEnrolledToSchoolClass(
                enterEnglishLanguageDevelopmentRecordCommand.studentId(),
                enterEnglishLanguageDevelopmentRecordCommand.schoolClassId())) {
            throw new IllegalArgumentException("Student is not enrolled to the school class");
        }

        EnglishLanguageDevelopmentRecordAggregate englishLanguageDevelopmentRecordAggregate = EnglishLanguageDevelopmentRecordMapper
                .toEnglishLanguageDevelopmentRecordAggregate(enterEnglishLanguageDevelopmentRecordCommand);

        this.englishLanguageDevelopmentRecordRepository.save(englishLanguageDevelopmentRecordAggregate);

        this.englishLanguageDevelopmentRecordEventPublisher.publish(
                new EnglishLanguageDevelopmentRecordEnteredEvent(englishLanguageDevelopmentRecordAggregate.getId()));

        return englishLanguageDevelopmentRecordAggregate.getId();
    }

}
