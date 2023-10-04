package br.com.alura.forum.repository

import br.com.alura.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import br.com.alura.forum.dto.TopicByCategoryDto

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByCourseName(nameCourse: String, pagination: Pageable): Page<Topic>

    @Query("SELECT new br.com.alura.forum.dto.TopicByCategoryDto(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun relatory(): List<TopicByCategoryDto>

    @Query("SELECT t from Topic t where t.answers is empty ")
    fun topicsNotAnswered(): List<Topic>
}