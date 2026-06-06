package br.nom.corbal.denison.elunari.registration.student.domain.repository;

import br.nom.corbal.denison.elunari.registration.student.domain.model.entity.StudentEntity;

public interface StudentRepository {
    public StudentEntity save(StudentEntity student);
}
