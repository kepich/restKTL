package com.restful.rest.controllers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import org.springframework.beans.factory.annotation.Autowired

import java.util.Optional

import com.restful.rest.model.Tag
import com.restful.rest.model.Task

import com.restful.rest.repos.TagRepository
import com.restful.rest.repos.TaskRepository

import com.restful.rest.response.TagResponse

import com.restful.rest.exceptions.NotFoundException
import com.restful.rest.exceptions.DuplicateEntryException

@RestController
@RequestMapping("tag")
class TagController{
    @Autowired
    lateinit var tag_repos: TagRepository
    @Autowired
    lateinit var task_repos: TaskRepository

    
    @PostMapping()
    fun tag_create(@RequestBody tag: Tag): Tag{
        var temp: Iterable<Tag> = tag_repos.findByTitle(tag.title)
        if (temp.count() != 0){
            var title_created: String = tag.title
            throw DuplicateEntryException("Object Tag with title: $title_created already created")
        }
        return tag_repos.save(tag)
    }

    @PostMapping("{id}")
    fun tag_update(@PathVariable id: Long, @RequestBody tag: Tag): Tag{
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent()){
            throw NotFoundException("Object Tag with tag: $id not found")
        }
        temp.get()
        var new_tag: Tag = Tag(title=tag.title, id=id)

        return tag_repos.save(new_tag)
    }

    // Debug only ***********************
    @GetMapping("all")
    fun all_tags() = tag_repos.findAll()
    //***********************************

    @GetMapping("{id}")
    fun get_one_by_id(@PathVariable id: Long): TagResponse{
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent())
            throw NotFoundException("Object Tag with tag: $id not found")
        
        var tag_with_tasks: TagResponse = TagResponse(temp.get(), task_repos.findByTaguid(id))
        return tag_with_tasks
    }

    @DeleteMapping("{id}")
    fun tag_delete(@PathVariable id: Long){
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent()){
            throw NotFoundException("Object Tag with id: $id not found")
        }
        tag_repos.delete(temp.get())

        task_repos.deleteAll(task_repos.findByTaguid(id))
    }
}