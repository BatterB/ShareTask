package com.example.domain.usecases

import com.example.domain.repository.TaskRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.*
import kotlin.test.assertEquals

class TaskListUseCaseTest {
    private val taskRepository = mock<TaskRepository>()

    companion object {
        const val TITLE = "title"
        const val EMPTY_TITLE = ""
        const val STRING_PRIORITY = "Standard priority"
        const val INT_PRIORITY = 3
        val DATE = Date()
    }

    @Test
    fun `should return success of add new task`(): Unit = runBlocking {
        Mockito.`when`(taskRepository.addNewTask(TITLE,INT_PRIORITY,DATE)).thenReturn(true)

        val useCase = TaskListUseCaseImpl(taskRepository)

        val actual = useCase.addNewTask(TITLE, STRING_PRIORITY, DATE)
        val expected = true

        assertEquals(expected, actual)
    }

    @Test
    fun `should return error of add new task`(): Unit = runBlocking {
        Mockito.`when`(taskRepository.addNewTask(TITLE,INT_PRIORITY,DATE)).thenReturn(true)

        val useCase = TaskListUseCaseImpl(taskRepository)

        val actual = useCase.addNewTask(EMPTY_TITLE, STRING_PRIORITY, DATE)
        val expected = false

        assertEquals(expected, actual)
    }
}