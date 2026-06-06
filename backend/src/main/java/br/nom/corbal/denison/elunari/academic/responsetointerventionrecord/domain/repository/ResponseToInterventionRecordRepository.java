package br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.repository;

import br.nom.corbal.denison.elunari.academic.responsetointerventionrecord.domain.model.aggregate.ResponseToInterventionRecordAggregate;

public interface ResponseToInterventionRecordRepository {
    public ResponseToInterventionRecordAggregate save(
            ResponseToInterventionRecordAggregate responseToInterventionRecordAggregate);
}
