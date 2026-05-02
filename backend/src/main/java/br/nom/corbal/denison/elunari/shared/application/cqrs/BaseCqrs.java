package br.nom.corbal.denison.elunari.shared.application.cqrs;

public sealed interface BaseCqrs permits BaseCommand, BaseQuery {

}