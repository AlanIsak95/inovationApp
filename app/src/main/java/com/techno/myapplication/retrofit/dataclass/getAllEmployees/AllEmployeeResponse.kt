package com.techno.myapplication.retrofit.dataclass.getAllEmployees

import com.techno.myapplication.retrofit.dataclass.getUserById.Data

data class AllEmployeeResponse(
	val data: List<Data>? = null,
	val message: String? = null,
	val status: String? = null
)
