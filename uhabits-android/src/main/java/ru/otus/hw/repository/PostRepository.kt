package ru.otus.hw.repository

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import ru.gildor.coroutines.okhttp.await
import ru.otus.hw.domain.ResourceState
import ru.otus.hw.model.Post
import javax.inject.Inject

interface PostRepository {
    suspend fun fetchPost(): ResourceState<Post>
}

class PostRepositoryImpl @Inject constructor(
    private val baseUrl: String,
    private val gson: Gson,
    private val okHttpClient: OkHttpClient
) : PostRepository {

    override suspend fun fetchPost(): ResourceState<Post> {
        val requestBody = FormBody.Builder()
            .add("name", "Не забывать про состояние базы данных")
            .build()

        val request = Request.Builder()
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .url("$baseUrl/habit")
            .build()

        val response = okHttpClient.newCall(request).await()
        if (response.isSuccessful) {
            return try {
                val post = gson.fromJson(response.body?.string(), Post::class.java)
                ResourceState.success(post)
            } catch (e: JsonSyntaxException) {
                ResourceState.error(e.message.toString(), null)
            }
        }
        return ResourceState.error(response.message, null)
    }
}