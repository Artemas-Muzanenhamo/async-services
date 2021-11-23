package com.artemas.university.web

import com.artemas.university.domain.Student
import com.artemas.university.domain.Tutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2

@RestController
@RequestMapping("/api")
class UniversityEndpoint(val webClient: WebClient = create()) {

    @GetMapping("/university")
    fun getAllStudentsAndTutors(): Flux<Tuple2<Student, Tutor>> {
        return webClient.get()
            .uri(STUDENTS_API)
            .retrieve()
            .bodyToFlux(Student::class.java)
            .log("STUDENTS CALLED.")
            .zipWith(
                webClient.get()
                    .uri(TUTORS_API)
                    .retrieve()
                    .bodyToFlux(Tutor::class.java)
                    .log("TUTORS CALLED.")
            )
    }

    companion object {
        const val STUDENTS_API = "http://localhost:8082/api/students"
        const val TUTORS_API = "http://localhost:8081/api/tutors"
    }
}