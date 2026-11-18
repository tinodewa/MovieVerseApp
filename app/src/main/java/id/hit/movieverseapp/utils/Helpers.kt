package id.hit.movieverseapp.utils

import id.hit.movieverseapp.common.ResultState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun formatRuntime(minutes: Int?): String {
    if (minutes == null || minutes <= 0) return "N/A"
    val hours = minutes / 60
    val mins = minutes % 60
    return if (hours > 0) "${hours}h ${mins}m" else "${mins}m"
}

inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading())
    try {
        val result = apiCall()
        emit(ResultState.Success(result))
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        emit(ResultState.Error(e.toReadableErrorMessage()))
    }
}

fun Throwable.toReadableErrorMessage(): String {
    return when (this) {
        is UnknownHostException -> "No internet connection. Please check your network."
        is SocketTimeoutException -> "The connection to the server took too long (timeout). Please try again later."
        is IOException -> "There is a network issue. Please check your internet connection."
        is SerializationException -> "Failed to process data from the server."
        is HttpException -> {
            val errorBody = response()?.errorBody()?.string()
            val tmdbMessage = try {
                errorBody?.let {
                    Json.parseToJsonElement(it)
                        .jsonObject["status_message"]
                        ?.jsonPrimitive?.content
                }
            } catch (_: Exception) {
                null
            }

            if (!tmdbMessage.isNullOrBlank()) {
                tmdbMessage
            } else {
                when (code()) {
                    401 -> "Access denied (401). Invalid API Key."
                    404 -> "The requested data was not found (404)."
                    429 -> "Too many requests to the server (429). Please wait a moment."
                    in 500..599 -> "The TMDB server is experiencing an issue (${code()}). Please try again later."
                    else -> "A server error occurred (Kode: ${code()})."
                }
            }
        }
        else -> localizedMessage ?: "An unknown error occurred."
    }
}