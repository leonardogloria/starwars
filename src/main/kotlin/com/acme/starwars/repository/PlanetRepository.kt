package com.acme.starwars.repository

import com.acme.starwars.model.Planet
import org.springframework.data.mongodb.core.*
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono  

@Repository
class PlanetRepository(private val template: ReactiveMongoTemplate) {
    fun create(planet: Mono<Planet>) = template.save(planet)
    fun getAll(): Flux<Planet> = template.findAll(Planet::class.java)
    fun getById(id: Int): Mono<Planet> = template.findById(id)
    fun delete(id: Int ) = template.remove<Planet>(Query(where("_id").isEqualTo(id)))
    fun search(nameFilter: String) = template.find<Planet>(Query(where("name").regex(".*$nameFilter.*","i")))



}