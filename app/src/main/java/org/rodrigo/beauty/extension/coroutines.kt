package org.rodrigo.beauty.extension

import androidx.lifecycle.liveData
import kotlinx.coroutines.Deferred
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal const val DEFAULT_TIMEOUT = 5000L

fun <T> Deferred<T>.asLiveData(
    context: CoroutineContext = EmptyCoroutineContext,
    timeoutInMs: Long = DEFAULT_TIMEOUT
) = liveData(context, timeoutInMs) {
    emit(this@asLiveData.await())
}