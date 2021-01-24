package com.tom.domain.usecase.base

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

typealias SimpleResult = Result<Any, Error>

abstract class BaseUseCase<in Params> : CoroutineScope {
//    region Members

    private val parentJobs = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default
    protected val resultChannel = Channel<SimpleResult>()

    val receiveChannel: ReceiveChannel<SimpleResult> = resultChannel

    override val coroutineContext: CoroutineContext
        get() = parentJobs + mainDispatcher

    //    region Functions
    protected abstract suspend fun run(params: Params)

    operator fun invoke(params: Params) {
        launch {
            withContext(backgroundDispatcher) {
                run(params)
            }
        }
    }

    protected fun <T> startAsync(block: suspend () -> T): Deferred<T> = async(parentJobs) {
        block()
    }

    fun clear() {
        resultChannel.close()
        parentJobs.cancel()
    }
}

class None : Any()