package com.acme.starwars.router

import com.acme.starwars.handler.PlanetHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class PlanetRouter(private val planetHandler: PlanetHandler) {

    @Bean
    fun planetRoutes() = router {
        "/planet".nest {
            GET("/{id}",planetHandler::getById)
            POST("/",planetHandler::create)
            DELETE("/{id}",planetHandler::delete)
            GET("/", planetHandler::getAll)
            PUT("/{id}",planetHandler::update)
        }
        "/planets".nest {
            GET("/",planetHandler::search)
        }

    }


}