package ru.otus.hw.repository

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await
import ru.otus.hw.domain.ResourceState
import ru.otus.hw.model.Delete
import javax.inject.Inject

interface DeleteRepository {
    suspend fun fetchDeleteById(deleteId: Int): ResourceState<Delete>
}

class DeleteRepositoryImpl @Inject constructor(
    private val baseUrl: String,
    private val gson: Gson,
    private val okHttpClient: OkHttpClient
) : DeleteRepository {

    override suspend fun fetchDeleteById(deleteId: Int): ResourceState<Delete> {
        val request = Request.Builder()
            .delete()
            .addHeader("Content-Type", "application/json")
            .url("$baseUrl/habit/:$deleteId")
            .build()

        val response = okHttpClient.newCall(request).await()
        if (response.isSuccessful) {
            return try {
                val delete = gson.fromJson(response.body?.string(), Delete::class.java)
                ResourceState.success(delete)
            } catch (e: JsonSyntaxException) {
                ResourceState.error(e.message.toString(), null)
            }
        }
        return ResourceState.error(response.message, null)
    }
}