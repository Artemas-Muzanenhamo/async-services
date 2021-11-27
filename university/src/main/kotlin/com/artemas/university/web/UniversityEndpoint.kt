package com.artemas.university.web

import com.artemas.university.domain.Student
import com.artemas.university.domain.Tutor
import com.artemas.university.domain.University
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class UniversityEndpoint(val webClient: WebClient = create()) {


    fun students(): Flux<Student> = webClient.get()
        .uri(STUDENTS_API)
        .retrieve()
        .bodyToFlux<Student>()
        .log("STUDENTS API WAS CALLED")

    fun tutor(tutorId: Long): Mono<Tutor> = webClient.get()
        .uri("$TUTORS_API/$tutorId")
        .retrieve()
        .bodyToMono<Tutor>()
        .log("TUTOR API WAS CALLED")


    @GetMapping("/university")
    fun getAllStudentsAndTutors(): Flux<University> {
        return this.students()
            .flatMap { student ->
                this.tutor(student.tutorId)
                    .map { tutor -> University(student, tutor) }
            }
    }

    companion object {
        const val STUDENTS_API = "http://localhost:8082/api/students"
        const val TUTORS_API = "http://localhost:8081/api/tutors"
    }
}