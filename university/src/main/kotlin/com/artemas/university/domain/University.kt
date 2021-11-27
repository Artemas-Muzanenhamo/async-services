package com.artemas.university.domain

data class University(
    val students: Student,
    val tutors: Tutor
)

data class Student(
    val id: Long,
    val name: String,
    val surname: String,
    val tutorId: Long
)

data class Tutor(
    val id: Long,
    val name: String,
    val surname: String
)
