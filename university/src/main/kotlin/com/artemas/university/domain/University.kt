package com.artemas.university.domain

import reactor.core.publisher.Flux

data class University(
    val students: Flux<Student>,
    val tutors: Flux<Tutor>
)

data class Student(
    val id: Long,
    val name: String,
    val surname: String
)

data class Tutor(
    val id: Long,
    val name: String,
    val surname: String
)
