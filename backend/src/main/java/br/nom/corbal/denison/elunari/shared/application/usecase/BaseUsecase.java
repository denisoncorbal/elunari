package br.nom.corbal.denison.elunari.shared.application.usecase;

public interface BaseUsecase<R, A extends BaseCqrs> {
    public R execute(A commandOrQuery);
}
