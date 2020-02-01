package com.restful.rest.controllers
import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.beans.factory.annotation.Autowired
import com.restful.rest.repos.TagRepository
import com.restful.rest.repos.TaskRepository
import com.restful.rest.Greeting

import kotlin.Any


@Controller
class MainRestController{
    val counter = AtomicLong()

    @Autowired
    lateinit var tag_repos: TagRepository
    @Autowired
    lateinit var task_repos: TaskRepository

    @GetMapping("/greeting")
    fun greeting(model: Model): String{
        return "greeting"
    }

    // DEBUG ONLY

    @GetMapping("/tasks")
    fun tasks(model: Model): String{
        model.addAttribute("tasks", task_repos.findAll())
        return "tasks"
    }
}