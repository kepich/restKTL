package com.restful.rest

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.hamcrest.Matchers.containsString

import org.mockito.Mock
import org.mockito.Mockito

import com.restful.rest.repos.TagRepository
import com.restful.rest.repos.TaskRepository

import com.restful.rest.model.Tag
import com.restful.rest.model.Task

import com.restful.rest.controllers.TagController
import com.restful.rest.controllers.TaskController


import java.util.Date

@SpringBootTest
@AutoConfigureMockMvc
class RestApplicationTests {
	@Mock
	lateinit var tagRepos: TagRepository
	@Mock
	lateinit var taskRepos: TaskRepository

	@Autowired
	lateinit var mockMVC: MockMvc

	@Autowired
	lateinit var taskController: TaskController
	@Autowired
	lateinit var tagController: TagController	

	@Test
	fun testTagReposSave() {
		var tag: Tag = Tag("")
		Mockito.doReturn(tag).`when`(tagRepos).save(tag)
		assertNotNull(tagRepos.save(tag))
	}

	@Test
	fun testTaskReposSave() {
		var task: Task = Task("", "", Date(), 0)
		Mockito.doReturn(task).`when`(taskRepos).save(task)
		assertNotNull(taskRepos.save(task))
	}

	@Test
	fun testTagRepos() {
		assertNotNull(tagRepos.findAll())
	}

	@Test
	fun testTaskRepos() {
		assertNotNull(taskRepos.findAll())
	}

	@Test
	fun testTaskController() {
		mockMVC.perform(get("/tasks"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Task list")))
	}

	@Test
	fun testTagController() {
		mockMVC.perform(get("/tag/all"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Tags list")))
	}

	@Test
	fun testLoginController() {
		mockMVC.perform(get("/tag/0"))
			.andExpect(status().is4xxClientError())
	}
}
