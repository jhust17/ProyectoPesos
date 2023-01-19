package com.proy.kmanager1.model

import java.util.Date

/*
    "id": 1,
	"nombre": "danienll",
	"fecha_nac": "2022-02-05",
	"correo": "dan@",
	"usuario": "daniell",
	"password": "12345"
*/
data class User(
    val id: Int,
    val nombre: String,
    val fecha_nac: Date,
    val correo: String,
    val usuario: String,
    val password: String,
)
