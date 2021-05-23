package com.techno.myapplication.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techno.myapplication.R
import com.techno.myapplication.retrofit.dataclass.getUserById.Data
import com.techno.myapplication.ui.EmployeeDetailActivity
import kotlin.collections.ArrayList


class HomeAdapter (items: List<Data>, context : Context? ): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var items: MutableList<Data> = ArrayList()
    private var context = context

    init {
        this.items = items.toMutableList()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false))
    }


    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                val employeeDetailActivity = Intent(context,EmployeeDetailActivity::class.java)

                with(items[adapterPosition]){

                    employeeDetailActivity.apply {
                        putExtra("id"    ,id)
                        putExtra("name"  ,employee_name)
                        putExtra("salary",employee_salary)
                        putExtra("age"   ,employee_age)
                    }

                }


                context?.startActivity(employeeDetailActivity)
            }
        }

        val idTv           :TextView  = view.findViewById(R.id.itemIdTV)
        val nameTV         :TextView  = view.findViewById(R.id.itemNameTV)
        val salaryTV       :TextView  = view.findViewById(R.id.itemSalaryTV)
        val ageTV          :TextView  = view.findViewById(R.id.itemAgeTV)

    }





    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        with(holder){

            idTv.text       = "ID: "+item.id.toString()
            nameTV.text     = "name: "+item.employee_name.toString()
            salaryTV.text   = "$"+item.employee_salary.toString()
            ageTV.text      = "age: "+item.employee_age.toString()

            when(item.employee_age){

                in 26..34 -> ageTV.setTextColor(Color.GREEN)
                else -> ageTV.setTextColor(Color.RED)

            }
        }




    }


    override fun getItemCount(): Int {
        return items.size
    }





}