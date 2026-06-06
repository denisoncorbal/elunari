package br.nom.corbal.denison.elunari.academic.assignement.domain.repository;

import br.nom.corbal.denison.elunari.academic.assignement.domain.model.aggregate.AssignementAggregate;

public interface AssignementRepository {
    public AssignementAggregate save(AssignementAggregate assignementAggregate);
}
