package com.restful.rest.controllers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus

import org.springframework.stereotype.Controller

import org.springframework.http.HttpStatus

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.ui.Model

import java.util.Optional

import com.restful.rest.model.Tag
import com.restful.rest.model.Task

import com.restful.rest.repos.TagRepository
import com.restful.rest.repos.TaskRepository

import com.restful.rest.response.TagResponse

import com.restful.rest.exceptions.NotFoundException
import com.restful.rest.exceptions.DuplicateEntryException

@Controller
@RequestMapping("tag")
class TagController{
    @Autowired
    lateinit var tag_repos: TagRepository
    @Autowired
    lateinit var task_repos: TaskRepository

    
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    fun tag_create(@RequestBody tag: Tag){
        var temp: Iterable<Tag> = tag_repos.findByTitle(tag.title)
        if (temp.count() != 0){
            var title_created: String = tag.title
            throw DuplicateEntryException("Object Tag with title: $title_created already created")
        }
        tag_repos.save(tag)
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun tag_update(@PathVariable id: Long, @RequestBody tag: Tag){
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent()){
            throw NotFoundException("Object Tag with tag: $id not found")
        }
        tag_repos.save(Tag(title=tag.title, id=id))
    }

    // Debug only ***********************
    @GetMapping("all")
    fun all_tags(model: Model): String{
        model.addAttribute("tags", tag_repos.findAll())
        return "tags"
    }
    //***********************************

    @GetMapping("{id}")
    fun get_one_by_id(@PathVariable id: Long, model: Model): String{
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent())
            throw NotFoundException("Object Tag with tag: $id not found")
        
        var tag_with_tasks: TagResponse = TagResponse(temp.get(), task_repos.findByTaguid(id))
        model.addAttribute("tag_resp", tag_with_tasks)
        return "tag"
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun tag_delete(@PathVariable id: Long){
        var temp: Optional<Tag> = tag_repos.findById(id)
        if (!temp.isPresent()){
            throw NotFoundException("Object Tag with id: $id not found")
        }
        tag_repos.deleteById(id)

        task_repos.deleteAll(task_repos.findByTaguid(id))
    }
}