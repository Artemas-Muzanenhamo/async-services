package com.artemas.university.web

import com.artemas.university.domain.Student
import com.artemas.university.domain.Tutor
import com.artemas.university.domain.University
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api")
class UniversityEndpoint(
    val webClient: WebClient = WebClient.create()
) {
    @GetMapping("/all")
    fun getAllStudentsAndTutors(): Flux<University> {
        val students: Flux<Student> = webClient.get()
            .uri("http://localhost:8082/students")
            .retrieve()
            .bodyToFlux(Student::class.java)
            .log()

        val tutors: Flux<Tutor> = webClient.get()
            .uri("http://localhost:8081/tutors")
            .retrieve()
            .bodyToFlux(Tutor::class.java)

        return Flux.just(University(students, tutors))
    }
}