package com.acme.starwars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StarwarsApplication

fun main(args: Array<String>) {
    runApplication<StarwarsApplication>(*args)
}
