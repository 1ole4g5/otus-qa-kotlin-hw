package ru.otus.hw.tests

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import ru.otus.hw.domain.ResourceState
import ru.otus.hw.mock.HabitApiMockDispatcher
import ru.otus.hw.repository.*

class MyHabitsAPITests {

    private val repositoryPost: PostRepository =
        PostRepositoryImpl("http://127.0.0.1:${server.port}", Gson(), OkHttpClient())

    private val repositoryGet: GetRepository =
        GetRepositoryImpl("http://127.0.0.1:${server.port}", Gson(), OkHttpClient())

    private val repositoryDelete: DeleteRepository =
        DeleteRepositoryImpl("http://127.0.0.1:${server.port}", Gson(), OkHttpClient())

    companion object {

        lateinit var server: MockWebServer

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            server = MockWebServer()
            server.dispatcher = HabitApiMockDispatcher()
            server.start()
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            server.shutdown()
        }
    }

    @Test
    fun fetchGet() {
        runBlocking {
            val response = repositoryGet.fetchGet()
            assertEquals(ResourceState.FetchingStatus.SUCCESS, response.status)
            assertEquals("first habit", response.data?.name)
        }
    }

    @Test
    fun fetchPost() {
        runBlocking {
            val response = repositoryPost.fetchPost()
            assertEquals(ResourceState.FetchingStatus.SUCCESS, response.status)
            assertEquals("Не забывать про состояние базы данных", response.data?.name2)
        }
    }

    @Test
    fun fetchDelete() {
        runBlocking {
            val response = repositoryDelete.fetchDeleteById(1)
            assertEquals(ResourceState.FetchingStatus.SUCCESS, response.status)
            assertEquals(null, response.data?.name)
        }
    }
}