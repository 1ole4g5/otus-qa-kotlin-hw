package ru.otus.hw.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import ru.otus.hw.database.transactionMock

class HabitApiMockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/habit" -> MockResponse()
                .setResponseCode(200)
                .setBody(transactionMock()[0])
            "/habit/:1" -> MockResponse()
                .setResponseCode(200)
                .setBody(transactionMock()[1])
            else -> MockResponse()
                .setResponseCode(200)
                .setBody(
                    "{nothing to show}"
                )
        }
    }
}