package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class NewTopicForm(
    @field:NotEmpty @field:Size(min = 5, max = 500, message = "title must be have min of 5 letters and max of 500 letters") val title: String,
    @field:NotEmpty val message: String,
    @field:NotNull val courseId: Long,
    @field:NotNull val authorId: Long
)