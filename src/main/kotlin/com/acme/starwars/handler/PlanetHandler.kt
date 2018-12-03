package com.acme.starwars.handler

import com.acme.starwars.model.Planet
import com.acme.starwars.service.PlanetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class PlanetHandler(){
    @Autowired
    lateinit var planetService: PlanetService
    fun create(serverRequest: ServerRequest) = planetService.create(serverRequest.bodyToMono()).flatMap {
        created(URI.create("/planet/${it.id}")).build()
    }
    fun getById(serverRequest: ServerRequest) = planetService.getById(
            serverRequest.pathVariable("id").toInt()
    ).flatMap { ok().body(fromObject(it))
    }.switchIfEmpty(status(HttpStatus.NOT_FOUND).build())
    fun getAll(serverRequest: ServerRequest) = ok().body(planetService.getAll(),Planet::class.java)
    fun delete(serverRequest: ServerRequest) = planetService.delete(serverRequest.pathVariable("id").toInt()).flatMap {
        if(it) ok().build()
        else status(HttpStatus.NOT_FOUND).build()
    }
    fun search(serverRequest: ServerRequest) = ok().body(planetService
            .search(serverRequest.queryParam("nameFilter").orElse("")),Planet::class.java)
    fun update(serverRequest: ServerRequest) = planetService.update(serverRequest.pathVariable("id").toInt()
            ,serverRequest.bodyToMono()).flatMap { created(URI.create("/planet/${it.id}")).build() }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

}