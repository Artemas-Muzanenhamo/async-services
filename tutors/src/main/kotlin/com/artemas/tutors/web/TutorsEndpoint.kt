package com.artemas.tutors.web

import com.artemas.tutors.domain.Tutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api")
class TutorsEndpoint {

    @GetMapping("/tutors")
    fun getAllTutors(): Flux<Tutor> {
        return Flux.just(
            Tutor(12313, "lebron", "james"),
            Tutor(54323, "kobe", "bryant"),
            Tutor(89453, "kevin", "garnett"),
            Tutor(99434, "steve", "nash")
        )
    }
}