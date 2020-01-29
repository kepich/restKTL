package com.restful.rest.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class Tag(
    val title: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1) {
    
    private constructor(): this("")
}