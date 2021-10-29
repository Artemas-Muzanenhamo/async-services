package com.artemas.university.domain

data class University(
    val students: List<Student>,
    val tutors: List<Tutor>
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
