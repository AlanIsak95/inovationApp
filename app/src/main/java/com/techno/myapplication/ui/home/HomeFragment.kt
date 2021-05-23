package com.techno.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techno.myapplication.R
import com.techno.myapplication.retrofit.dataclass.getAllEmployees.AllEmployeeResponse

class HomeFragment : Fragment() , HomePresenter.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    private val homePresenter = HomePresenter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home,container,false)
        intitView(root)
        return root

    }

    private fun intitView(root: View) {

        with(root){
            recyclerView = findViewById(R.id.recyclerView)
            progress     = findViewById(R.id.progressBarHome)
        }

        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        homePresenter.setView(this)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        executeGetAllUsersService()
    }

    private fun executeGetAllUsersService() {
        showProgressBar(true)
        homePresenter.executeHomeService()
    }

    private fun showProgressBar(boolean: Boolean){

        if (boolean){

            progress.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

        }else{
            progress.visibility = View.GONE
            recyclerView.visibility  = View.VISIBLE

        }


    }

    override fun getAllEmployeesResponse(response: AllEmployeeResponse) {

        response.data?.let {


            val adapter = HomeAdapter(it,context)
            recyclerView.adapter = adapter
            showProgressBar(false)


        }?:run{
            showError("Error Data Null")
        }


    }

    override fun showError(error: String) {

        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
        showProgressBar(false)
    }


}