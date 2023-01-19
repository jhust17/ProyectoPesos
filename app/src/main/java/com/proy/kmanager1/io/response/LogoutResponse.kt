package com.proy.kmanager1.io.response

data class LogoutResponse(
    val error: Boolean,
    val status: Int,
    val body: String,
)
