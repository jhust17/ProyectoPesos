package com.proy.kmanager1.io.response

import com.proy.kmanager1.model.Logg
import com.proy.kmanager1.model.User

/*"error": false,
	"status": 200,
	"body":
	*/
data class LoginResponse(
    val error: Boolean,
    val status: Int,
    val body: String,
    val user: User,
    val logg: Logg
)
