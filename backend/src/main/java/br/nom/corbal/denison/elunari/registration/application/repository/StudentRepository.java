package br.nom.corbal.denison.elunari.registration.application.repository;

import br.nom.corbal.denison.elunari.registration.domain.Student;

public interface StudentRepository {
    public Student save(Student student);
}
