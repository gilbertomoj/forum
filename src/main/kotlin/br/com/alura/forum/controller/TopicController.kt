package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.TopicService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    @Cacheable("topics")
    fun list(@RequestParam(required = false) nameCourse: String?,@PageableDefault(size = 3, sort = ["createdAt"], direction = Sort.Direction.DESC ) pagination: Pageable): Page<TopicView> {
        return service.list(nameCourse, pagination)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): TopicView {
        return service.getById(id)
    }

    @PostMapping()
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun register(@RequestBody @Valid form: NewTopicForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicView> {
        val topicView = service.register(form)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping()
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(@RequestBody dto: UpdateTopicForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicView> {
        val topicView = service.update(dto)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun delete(@PathVariable id: Long){
        service.delete(id)
    }

    @GetMapping("/relatory")
    fun getRelatory(): List<TopicByCategoryDto> {
        return service.relatory()
    }

    @GetMapping("/notanswered")
    fun getTopicsNotAnswered(): List<Topic> {
        return service.notanswered()
    }
}