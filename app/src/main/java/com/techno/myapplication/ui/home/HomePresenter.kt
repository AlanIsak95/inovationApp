package com.techno.myapplication.ui.home

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techno.myapplication.retrofit.dataclass.getAllEmployees.AllEmployeeResponse
import com.techno.myapplication.retrofit.repository.RetrofitRepository
import com.techno.myapplication.utils.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomePresenter: BasePresenter<HomePresenter.View>() {

    private var serviceManager  = RetrofitRepository()

    /**
     * Interfaces implementadas en MainActivity
     */
    interface View : BasePresenter.View {
        fun getAllEmployeesResponse(response: AllEmployeeResponse)
        fun showError(error: String = "Sin Informacion")
    }


    fun executeHomeService() {

        serviceManager.getEndPointConnection()?.getAllEmployees()?.enqueue(object : Callback<AllEmployeeResponse> {

            override fun onResponse(call: Call<AllEmployeeResponse>, response: Response<AllEmployeeResponse>) {

                getView()?.let { view ->
                    with(view) {
                        when {
                            response.code() == 200 -> response.body()?.let { getAllEmployeesResponse(it) } ?: run {showError()}
                            else -> showError("Service Error")
                        }

                    }

                } ?: run {
                    getView()?.showError("Service Error")
                }


            }

            override fun onFailure(call: Call<AllEmployeeResponse>, t: Throwable) {
                getView()?.showError("Service Error")
            }


        })



    }






}