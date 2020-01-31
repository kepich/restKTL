package com.restful.rest.controllers
import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import com.restful.rest.repos.TagRepository
import com.restful.rest.repos.TaskRepository
import com.restful.rest.Greeting

@RestController
class MainRestController{
    val counter = AtomicLong()

    @Autowired
    lateinit var tag_repos: TagRepository
    @Autowired
    lateinit var task_repos: TaskRepository

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting(counter.incrementAndGet(), "Hello, $name")

    // DEBUG ONLY

    @GetMapping("/tasks")
    fun tasks() =
        task_repos.findAll()
}