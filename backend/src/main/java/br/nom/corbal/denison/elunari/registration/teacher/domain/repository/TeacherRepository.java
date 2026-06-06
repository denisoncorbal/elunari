package br.nom.corbal.denison.elunari.registration.teacher.domain.repository;

import br.nom.corbal.denison.elunari.registration.teacher.domain.model.entity.TeacherEntity;

public interface TeacherRepository {
    public TeacherEntity save(TeacherEntity teacher);
}
