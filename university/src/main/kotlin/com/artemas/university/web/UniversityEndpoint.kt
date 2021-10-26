package com.artemas.university.web

import com.artemas.university.domain.Student
import com.artemas.university.domain.Tutor
import com.artemas.university.domain.University
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2

@RestController
@RequestMapping("/api")
class UniversityEndpoint(
    val webClient: WebClient = WebClient.create()
) {
    @GetMapping("/university")
    fun getAllStudentsAndTutors(): Flux<Tuple2<Student, Tutor>> {
        val students: Flux<Student> = webClient.get()
            .uri("http://localhost:8082/api/students")
            .retrieve()
            .bodyToFlux(Student::class.java)
            .log("STUDENTS CALLED.")

        val tutors: Flux<Tutor> = webClient.get()
            .uri("http://localhost:8081/api/tutors")
            .retrieve()
            .bodyToFlux(Tutor::class.java)
            .log("TUTORS CALLED.")

        return students.zipWith(tutors)
    }
}