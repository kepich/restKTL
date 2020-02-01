package com.restful.rest.controllers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import com.restful.rest.repos.TagRepository
import com.restful.rest.model.Tag
import com.restful.rest.repos.TaskRepository
import com.restful.rest.model.Task
import com.restful.rest.response.TagResponse
import java.util.Optional
import com.restful.rest.exceptions.NotFoundException

@RestController
@RequestMapping("tag")
class TagController{
    @Autowired
    lateinit var tag_repos: TagRepository
    @Autowired
    lateinit var task_repos: TaskRepository

    // Debug only
    @GetMapping()
    fun tags() =
        tag_repos.findAll()

    @GetMapping("{id}")
    fun get_one_by_id(@PathVariable id: Long): TagResponse{
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent())
            throw NotFoundException("Object with tag: $id not found")
        
        var tag_with_tasks: TagResponse = TagResponse(temp.get(), task_repos.findByTaguid(id))
        return tag_with_tasks
    }
    /*
        Необходимо создать data class TagResponse, который будет содержать список задач и сам тег, чтобы потом вернуть его в виде JSON
     */
}