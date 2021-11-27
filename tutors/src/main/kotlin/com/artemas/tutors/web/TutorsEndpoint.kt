package com.artemas.tutors.web

import com.artemas.tutors.domain.Tutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/api")
class TutorsEndpoint {

    val tutors = listOf(
        Tutor(12313, "lebron", "james"),
        Tutor(54323, "kobe", "bryant"),
        Tutor(89453, "kevin", "garnett"),
        Tutor(99434, "steve", "nash")
    )

    @GetMapping("/tutors")
    fun getAllTutors(): Flux<Tutor> {
        return Flux.fromIterable(tutors)
    }

    @GetMapping("/tutors/{tutorId}")
    fun getTutorById(@PathVariable tutorId: Long): Mono<Tutor> {
        return Flux.fromIterable(tutors)
            .filter { it.id == tutorId }
            .toMono()
    }
}