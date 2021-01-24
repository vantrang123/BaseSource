package com.tom.base.util

import android.content.Context
import com.tom.base.R
import javax.inject.Inject

class DefaultErrorFactory @Inject constructor(val context: Context) : ErrorFactory {
    override fun createEmptyErrorMessage(): String = context.getString(R.string.empty_state_message)

    override fun createApiErrorMessage(e: Exception): String = e.message.toString()
}
