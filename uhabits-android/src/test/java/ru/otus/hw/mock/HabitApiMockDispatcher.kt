package ru.otus.hw.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class HabitApiMockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/habit" -> MockResponse()
                .setResponseCode(200)
                .setBody("{\"name\": \"first habit\", \"name2\": \"Не забывать про состояние базы данных\", \"name3\": \"third habit\"}")
            "/habit/:1" -> MockResponse()
                .setResponseCode(200)
                .setBody("{\"name2\": \"Не забывать про состояние базы данных\", \"name3\": \"third habit\"}")
            else -> MockResponse()
                .setResponseCode(200)
                .setBody(
                    "{nothing to show}"
                )
        }
    }
}