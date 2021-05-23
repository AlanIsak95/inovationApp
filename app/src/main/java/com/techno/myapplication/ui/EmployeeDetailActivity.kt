package com.techno.myapplication.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.techno.myapplication.R


class EmployeeDetailActivity : AppCompatActivity() {

    private lateinit var idDetailTV     : TextView
    private lateinit var nameDetailTV   : TextView
    private lateinit var salaryDetailTV : TextView
    private lateinit var ageDetailTV    : TextView

    private lateinit var idDetailValue     : String
    private lateinit var nameDetailValue   : String
    private  var salaryDetailValue : Int = 0
    private lateinit var ageDetailValue    : String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_detail)
        supportActionBar!!.title = "Employee Detail"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purple_500)))

        setView()
        getDataFromBundle()
        setDataFromBundle()


    }

    private fun setView() {

        idDetailTV     = findViewById(R.id.idDetailTV)
        nameDetailTV   = findViewById(R.id.nameDetailTV)
        salaryDetailTV = findViewById(R.id.salaryDetailTV)
        ageDetailTV    = findViewById(R.id.ageDetailTV)

    }

    private fun getDataFromBundle() {


        intent.extras?.let {

            with(it){
                idDetailValue     = getInt("id").toString()
                nameDetailValue   = getString("name").toString()
                salaryDetailValue = getInt("salary")
                ageDetailValue    = getInt("age").toString()
            }

        }



    }

    private fun setDataFromBundle() {

        idDetailTV.append(idDetailValue)
        nameDetailTV.append(nameDetailValue)
        salaryDetailTV.append(salaryDetailValue.toString())
        ageDetailTV.append(ageDetailValue)

        when(salaryDetailValue){
            in 0..86000 -> salaryDetailTV.setTextColor(Color.RED)
            else       -> salaryDetailTV.setTextColor(Color.GREEN)
        }

    }


}