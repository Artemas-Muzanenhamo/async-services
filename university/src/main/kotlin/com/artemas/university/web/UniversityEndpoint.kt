package com.artemas.university.web

import com.artemas.university.domain.Student
import com.artemas.university.domain.Tutor
import com.artemas.university.domain.University
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import org.springframework.web.reactive.function.client.bodyToFlow
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2

@RestController
@RequestMapping("/api")
class UniversityEndpoint(val webClient: WebClient = create()) {


    fun students(): Flux<Student> = webClient.get()
        .uri(STUDENTS_API)
        .retrieve()
        .bodyToFlux()

    fun tutors(): Flux<Tutor> = webClient.get()
        .uri(TUTORS_API)
        .retrieve()
        .bodyToFlux()


    @GetMapping("/university")
    fun getAllStudentsAndTutors(): Flux<University> {
        this.students()
            .map { student -> this.tutors() }
    }

    companion object {
        const val STUDENTS_API = "http://localhost:8082/api/students"
        const val TUTORS_API = "http://localhost:8081/api/tutors"
    }
}