package br.nom.corbal.denison.elunari.academic.domain.repository;

import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.AssignementAggregate;

public interface AssignementRepository {
    public AssignementAggregate save(AssignementAggregate assignementAggregate);
}
