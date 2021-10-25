package com.artemas.university

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UniversityApplication

fun main(args: Array<String>) {
    runApplication<UniversityApplication>(*args)
}
