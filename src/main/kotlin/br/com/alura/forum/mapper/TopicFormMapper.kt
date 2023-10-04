package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component
import br.com.alura.forum.model.Topic

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService,
): Mapper<NewTopicForm, Topic> {

    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.searchForId(t.courseId),
            author = userService.getUserById(t.authorId)
        )
    }
}