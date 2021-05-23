package com.techno.myapplication.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techno.myapplication.MainActivity
import com.techno.myapplication.R
import com.techno.myapplication.ui.login.LoginActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE)
        val userName = sharedPref.getString(getString(R.string.userSaved), "No Info")

        var intent: Intent?

        Timer().schedule( timerTask {


            intent = if (userName.equals("No Info"))
                Intent(this@SplashActivity,LoginActivity::class.java)
            else
                Intent(this@SplashActivity,MainActivity::class.java)


            startActivity(intent)
            finish()

        },3000)

    }
}