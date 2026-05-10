package br.nom.corbal.denison.elunari.academic.domain.repository;

import br.nom.corbal.denison.elunari.academic.domain.model.aggregate.ResponseToInterventionRecordAggregate;

public interface ResponseToInterventionRecordRepository {
    public ResponseToInterventionRecordAggregate save(
            ResponseToInterventionRecordAggregate responseToInterventionRecordAggregate);
}
