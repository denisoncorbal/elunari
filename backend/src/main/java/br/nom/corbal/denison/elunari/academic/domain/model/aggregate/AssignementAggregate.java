package br.nom.corbal.denison.elunari.academic.domain.model.aggregate;

import java.util.UUID;

import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementGrade;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementName;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementStatus;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.AssignementWeight;
import br.nom.corbal.denison.elunari.academic.domain.model.valueobject.QuarterReference;

public class AssignementAggregate {
    private UUID id;

    public UUID getId() {
        return id;
    }

    private UUID teacherId;
    private UUID schoolClassId;
    private UUID subjectId;
    private UUID studentId;
    private QuarterReference quarterReference;
    private AssignementName name;
    private AssignementWeight weight;
    private AssignementGrade grade;
    private AssignementStatus status;

    public AssignementAggregate(UUID teacherId, UUID schoolClassId, UUID subjectId, UUID studentId,
            QuarterReference quarterReference, AssignementName name, AssignementWeight weight, AssignementGrade grade,
            AssignementStatus status) {
        this.id = UUID.randomUUID();
        this.teacherId = teacherId;
        this.schoolClassId = schoolClassId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.quarterReference = quarterReference;
        this.name = name;
        this.weight = weight;
        this.grade = grade;
        this.status = status;
    }

}
