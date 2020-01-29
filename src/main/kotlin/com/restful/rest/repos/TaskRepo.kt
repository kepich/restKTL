package com.restful.rest.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import com.restful.rest.model.Task

@Repository
interface TaskRepository : CrudRepository<Task, Long>{
    fun findByName(name: String): Iterable<Task>
}