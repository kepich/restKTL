package com.restful.rest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

import org.springframework.stereotype.Controller

import org.springframework.http.HttpStatus

import org.springframework.beans.factory.annotation.Autowired

import java.util.Optional

import com.restful.rest.repos.TaskRepository
import com.restful.rest.repos.TagRepository

import com.restful.rest.model.Task
import com.restful.rest.model.Tag

import com.restful.rest.exceptions.NotFoundException
import com.restful.rest.exceptions.DuplicateEntryException

@Controller
@RequestMapping("task")
class TaskController{
    @Autowired
    lateinit var task_repos: TaskRepository

    @Autowired
    lateinit var tag_repos: TagRepository


    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    fun task_create(@RequestBody task: Task){
        var temp: Iterable<Task> = task_repos.findByName(task.name)
        if (temp.count() != 0){
            var name_created: String = task.name
            throw DuplicateEntryException("Object Task with name: $name_created already created")
        }
        var temp_tag: Optional<Tag> = tag_repos.findById(task.taguid)
        if (!temp_tag.isPresent()){
            var tag_id: Long = task.taguid
            throw NotFoundException("Object Tag with id: $tag_id not found")
        }
        task_repos.save(task)
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun task_update(@PathVariable id: Long, @RequestBody task: Task){
        var temp: Optional<Task> = task_repos.findById(id)
        if (!temp.isPresent())
            throw NotFoundException("Object Task with id: $id not found")
        var temp_tag: Optional<Tag> = tag_repos.findById(task.taguid)
        if (!temp_tag.isPresent()){
            var tag_id: Long = task.taguid
            throw NotFoundException("Object Tag with id: $tag_id not found")
        }
        task_repos.save(Task(name=task.name, description=task.description, task_date=task.task_date, taguid=task.taguid, id=id))
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun task_delete(@PathVariable id: Long){
        var temp: Optional<Task> = task_repos.findById(id)
        if (!temp.isPresent()){
            throw NotFoundException("Object Task with id: $id not found")
        }
        task_repos.deleteById(id)
    }

}