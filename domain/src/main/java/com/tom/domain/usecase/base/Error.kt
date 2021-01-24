package com.tom.domain.usecase.base

sealed class Error {
    object NetworkError: Error()
    object ResponseError: Error()
}