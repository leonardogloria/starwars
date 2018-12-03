package com.acme.starwars.service

import com.acme.starwars.model.Planet
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PlanetService {
    fun create(planet: Mono<Planet>):Mono<Planet>
    fun getById(id: Int): Mono<Planet>
    fun getAll(): Flux<Planet>
    fun delete(id: Int): Mono<Boolean>
    fun search(nameFilter: String): Flux<Planet>
    fun update(id:Int, planet: Mono<Planet>): Mono<Planet>
}