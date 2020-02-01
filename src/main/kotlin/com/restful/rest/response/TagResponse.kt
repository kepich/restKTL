package com.restful.rest.response
import com.restful.rest.model.Tag
import com.restful.rest.model.Task

data class TagResponse(val tag: Tag, val tasks: Iterable<Task>)