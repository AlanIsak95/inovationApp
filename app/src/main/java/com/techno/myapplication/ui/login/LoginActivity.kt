package com.techno.myapplication.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.techno.myapplication.MainActivity
import com.techno.myapplication.R
import com.techno.myapplication.retrofit.dataclass.getUserById.GetUserByIdResponse

class LoginActivity : AppCompatActivity(), LoginPresenter.View {

    private lateinit var loginbtn     : Button
    private lateinit var idEt         : EditText
    private lateinit var titletv      : TextView
    private lateinit var progressBar  : ProgressBar

    private val loginPresenter = LoginPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        setListeners()

    }

    private fun initView() {

            loginbtn = findViewById(R.id.loginbtn)
            idEt     = findViewById(R.id.userIdEd)
            titletv  = findViewById(R.id.titleTV)
            progressBar = findViewById(R.id.progressBar)

            loginPresenter.setView(this)

    }

    private fun setListeners() {

        loginbtn.setOnClickListener {

            val id : String = idEt.text.toString()
            if (id.isNotEmpty() && id.isNotBlank())
                executeGetServiceById(id)
            else
                Toast.makeText(this@LoginActivity,"Required field", Toast.LENGTH_LONG).show()


        }

    }

    private fun executeGetServiceById(id : String = "") {

        showProgressBar(true)
        loginPresenter.executeLoginService(id)


    }

    private fun showProgressBar(boolean: Boolean){

        if (boolean){

            progressBar.visibility = View.VISIBLE
            loginbtn.visibility = View.GONE
            idEt.visibility     = View.GONE
            titletv.visibility  = View.GONE

        }else{
            loginbtn.visibility = View.VISIBLE
            idEt.visibility     = View.VISIBLE
            titletv.visibility  = View.VISIBLE
            progressBar.visibility = View.GONE

        }


    }

    override fun getUserDataByIdResponse(response: GetUserByIdResponse) {


        response.data?.let {


            val sharePreferences = getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE)
            val editor = sharePreferences.edit()

            editor.apply{
                putString(getString(R.string.userSaved),it.employee_name)
            }.apply()


            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }?:run{
            showError("User Not Found")
        }



    }

    override fun showError(error: String) {

        Toast.makeText(this@LoginActivity, error, Toast.LENGTH_LONG).show()
        showProgressBar(false)

    }

}