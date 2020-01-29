package com.restful.rest.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import java.util.Date

@Entity
class Task(
    val name: String,
    val description: String,
    val task_date: Date,
    @Column(nullable=false) val tag_id: Long,
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1) {
    
    private constructor(): this("", "", Date(), 0)
}