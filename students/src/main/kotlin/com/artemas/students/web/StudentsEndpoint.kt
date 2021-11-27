package com.artemas.students.web

import com.artemas.students.domain.Student
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api")
class StudentsEndpoint {

    @GetMapping("/students")
    fun getAllStudents(): Flux<Student> {
        return Flux.just(
            Student(47834, "seth", "curry", 54323),
            Student(43578, "steph", "curry", 12313),
            Student(99854, "booker", "t", 99434),
            Student(48743, "dwight", "howard", 89453)
        )
    }
}