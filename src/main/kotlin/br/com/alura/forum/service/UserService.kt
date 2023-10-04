package br.com.alura.forum.service

import br.com.alura.forum.model.User
import br.com.alura.forum.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (private val repository: UserRepository){
    init{
    }

    fun getUserById(id: Long): User {
        return repository.getReferenceById(id)
    }
}
