package com.techno.myapplication.retrofit.interfaces

import com.techno.myapplication.retrofit.dataclass.getAllEmployees.AllEmployeeResponse
import com.techno.myapplication.retrofit.dataclass.getUserById.GetUserByIdResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ICoreServices {


    @GET("employees/")
    fun getAllEmployees() : Call<AllEmployeeResponse>



    @GET("employee/{id}/")
    fun getUserById(
        @Path("id") id : String ) : Call<GetUserByIdResponse>


}