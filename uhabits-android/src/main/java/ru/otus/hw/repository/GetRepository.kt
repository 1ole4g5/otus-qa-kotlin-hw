package ru.otus.hw.repository

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await
import ru.otus.hw.domain.ResourceState
import javax.inject.Inject

interface GetRepository {
    suspend fun fetchGet(): ResourceState<ru.otus.hw.model.Get>
}

class GetRepositoryImpl @Inject constructor(
    private val baseUrl: String,
    private val gson: Gson,
    private val okHttpClient: OkHttpClient
) : GetRepository {

    override suspend fun fetchGet(): ResourceState<ru.otus.hw.model.Get> {
        val request = Request.Builder()
            .get()
            .addHeader("Content-Type", "application/json")
            .url("$baseUrl/habit")
            .build()

        val response = okHttpClient.newCall(request).await()
        if (response.isSuccessful) {
            return try {
                val get = gson.fromJson(response.body?.string(), ru.otus.hw.model.Get::class.java)
                ResourceState.success(get)
            } catch (e: JsonSyntaxException) {
                ResourceState.error(e.message.toString(), null)
            }
        }
        return ResourceState.error(response.message, null)
    }
}