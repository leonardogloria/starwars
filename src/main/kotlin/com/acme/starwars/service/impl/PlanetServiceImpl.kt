package com.acme.starwars.service.impl

import com.acme.starwars.model.Planet
import com.acme.starwars.repository.PlanetRepository
import com.acme.starwars.service.PlanetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PlanetServiceImpl: PlanetService {
    @Autowired
    lateinit var planetRepository: PlanetRepository

    override fun create(planet: Mono<Planet>): Mono<Planet> = planetRepository.create(planet)
    override fun getById(id: Int): Mono<Planet> = planetRepository.getById(id)
    override fun getAll(): Flux<Planet> = planetRepository.getAll()
    override fun delete(id: Int): Mono<Boolean>  = planetRepository.delete(id).map { it.deletedCount > 0 }
    override fun search(nameFilter: String):Flux<Planet> = planetRepository.search(nameFilter)
    override fun update(id: Int, planet: Mono<Planet>) = planetRepository.update(id,planet)


}