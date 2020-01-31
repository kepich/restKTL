package com.restful.rest.controllers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import com.restful.rest.repos.TaskRepository

@RestController
@RequestMapping("task")
class TaskController{
    @Autowired
    lateinit var task_repos: TaskRepository

    @GetMapping()
    fun task() =
        task_repos.findAll()
}