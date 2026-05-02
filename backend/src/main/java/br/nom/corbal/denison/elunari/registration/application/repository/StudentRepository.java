package br.nom.corbal.denison.elunari.registration.application.repository;

import br.nom.corbal.denison.elunari.registration.domain.model.entity.StudentEntity;

public interface StudentRepository {
    public StudentEntity save(StudentEntity student);
}
