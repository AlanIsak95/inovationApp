package com.techno.myapplication.ui.login


import com.techno.myapplication.retrofit.dataclass.getUserById.GetUserByIdResponse
import com.techno.myapplication.retrofit.repository.RetrofitRepository
import com.techno.myapplication.utils.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter: BasePresenter<LoginPresenter.View>() {

    private var serviceManager : RetrofitRepository = RetrofitRepository()

    /**
     * Interfaces implementadas en LoginActivity
     */
    interface View : BasePresenter.View {
        fun getUserDataByIdResponse(response: GetUserByIdResponse)
        fun showError(error: String = "Sin Informacion")
    }


    fun executeLoginService(id: String = "") {

        serviceManager.getEndPointConnection()?.getUserById(id)?.enqueue(object : Callback<GetUserByIdResponse> {

            override fun onResponse(call: Call<GetUserByIdResponse>, response: Response<GetUserByIdResponse>) {

                getView()?.let { view ->
                    with(view) {
                        when(response.code()) {

                            200 -> response.body()?.let { getUserDataByIdResponse(it) } ?: run {showError()}
                            else -> showError("Service Error")

                        }

                    }

                } ?: run { getView()?.showError("Error") }


            }

            override fun onFailure(call: Call<GetUserByIdResponse>, t: Throwable) {
                getView()?.showError("Service Error")
            }


        })



    }






}