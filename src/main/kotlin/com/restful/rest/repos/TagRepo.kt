package com.restful.rest.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import com.restful.rest.model.Tag

@Repository
interface TagRepository : CrudRepository<Tag, Long>{
    fun findByTitle(title: String): Iterable<Tag>
}