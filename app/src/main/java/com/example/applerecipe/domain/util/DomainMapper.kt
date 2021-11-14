package com.example.applerecipe.domain.util


interface DomainMapper<T, DomainModel> {

    //Takes an entity then outputs a domain model
    fun mapToDomainModel(model: T): DomainModel

    //Takes a domain model then outputs an entity.
    fun mapFromDomainModel(domainModel: DomainModel):T
}