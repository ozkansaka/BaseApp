package com.ragnar.baseapp.common

data class Response<T>(
    val code: String?,
    val message: String?,
    val status: Int,
    val payload: T
)