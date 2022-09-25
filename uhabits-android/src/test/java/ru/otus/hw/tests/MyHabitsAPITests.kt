package ru.otus.hw.tests

import com.google.gson.Gson
import io.qameta.allure.kotlin.Allure.step
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Severity
import io.qameta.allure.kotlin.SeverityLevel
import io.qameta.allure.kotlin.Story
import io.qameta.allure.kotlin.junit4.AllureRunner
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import ru.otus.hw.domain.ResourceState
import ru.otus.hw.mock.HabitApiMockDispatcher
import ru.otus.hw.repository.*

@RunWith(AllureRunner::class)
@Feature("MyHabitsAPITests")
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
    @Story("Получение списка привычек")
    @Severity(value = SeverityLevel.NORMAL)
    fun fetchGet() {
        runBlocking {
            val response = repositoryGet.fetchGet()
            step("Проверяем успешный статус ответа сервера ${response.status}") {
                assertEquals(ResourceState.FetchingStatus.SUCCESS, response.status)
            }
            step("Проверяем, что параметр name со значением ${response.data?.name}") {
                assertEquals("first habit", response.data?.name)
            }
        }
    }


    @Test
    @Story("Создание привычки")
    @Severity(value = SeverityLevel.CRITICAL)
    fun fetchPost() {
        runBlocking {
            val response = repositoryPost.fetchPost()
            step("Проверяем успешный статус ответа сервера ${response.status}") {
                assertEquals(ResourceState.FetchingStatus.SUCCESS, response.status)
            }
            step("Проверяем, что привычка создана с необходимым именем ${response.data?.name2}") {
                assertEquals("Не забывать про состояние базы данных", response.data?.name2)
            }
        }
    }

    @Test
    @Story("Удаление привычки")
    @Severity(value = SeverityLevel.CRITICAL)
    fun fetchDelete() {
        runBlocking {
            val response = repositoryDelete.fetchDeleteById(1)
            step("Проверяем успешный статус ответа сервера ${response.status}") {
                assertEquals(ResourceState.FetchingStatus.SUCCESS, response.status)
            }
            step("Проверяем, что не существует параметра name со значение ${response.data?.name}") {
                assertEquals(null, response.data?.name)
            }
        }
    }
}
