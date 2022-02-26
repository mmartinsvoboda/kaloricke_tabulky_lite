package com.example.kaloricketabulkylite.utils.data

import android.accounts.AuthenticatorException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.CancellationException

abstract class BaseDataSource {

    protected suspend fun <T> getResult(
        name: String,
        printJson: Boolean = false,
        call: suspend () -> Response<T>
    ): Resource<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                if (printJson) Timber.tag(name).v(body.toString())
                if (body != null) return Resource.success(body)
            }

            return when (response.code()) {
                401 -> error(
                    name,
                    AuthenticatorException("${response.code()} - ${response.message()}")
                )
                else -> error(
                    name,
                    UnsuccessfulCallException("${response.code()} - ${response.message()}")
                )
            }

        } catch (e: CancellationException) {
            return error(name, e, false)
        } catch (e: Exception) {
            Timber.e(e)
            return error(name, e)
        }
    }

    private fun <T> error(
        name: String,
        throwable: Throwable,
        shouldLog: Boolean = true
    ): Resource<T> {
        if (shouldLog) Timber.e(throwable, "Network call $name has failed")
        return Resource.error(throwable)
    }

}

class UnsuccessfulCallException(message: String) : IOException(message)