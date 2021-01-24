package com.tom.base.util

interface ErrorFactory {
    fun createEmptyErrorMessage(): String

    fun createApiErrorMessage(e: Exception): String
}
