package com.restful.rest
import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import com.restful.rest.repos.TagRepository

@RestController
class GreetingController{
    val counter = AtomicLong()

    @Autowired
    lateinit var tag_repos: TagRepository

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/tasks")
    fun getAllTags() =
        tag_repos.findAll()
}