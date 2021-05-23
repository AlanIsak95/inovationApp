package com.techno.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.techno.myapplication.ui.login.LoginActivity
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var profileImage : CircleImageView
    private lateinit var titleNav : TextView
    private lateinit var logOutNav : TextView
    private val cameraRequestId =1222



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setNavigationItemSelectedListener(this@MainActivity)



        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        //profileImage= drawerLayout.findViewById(R.id.profileIV)

        with(navView.getHeaderView(0)){
            profileImage = findViewById(R.id.profileIV)
            titleNav     = findViewById(R.id.profileUserTV)
            logOutNav    = findViewById(R.id.closeProfileUserBtn)
        }

        val sharedPref = getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE)
        titleNav.text = sharedPref.getString(getString(R.string.userSaved), "No Info")+" (log in)"

        logOutNav.setOnClickListener{

            Toast.makeText(this@MainActivity, "See you!!",Toast.LENGTH_LONG).show()
            sharedPref.edit().clear().apply()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()

        }




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.title.toString()){

            getString(R.string.menu_gallery) -> {
                openCamera()
            }
            else ->
                drawerLayout.closeDrawer(GravityCompat.START)

        }

        return true
    }

    private fun openCamera() {

        if(ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(android.Manifest.permission.CAMERA),
                cameraRequestId
            )
            openCamera()
        }else{

         val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
         startActivityForResult(cameraInt, cameraRequestId)

        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == cameraRequestId){
            val images: Bitmap = data?.extras?.get("data") as Bitmap
            profileImage.setImageBitmap(images)
        }

    }
}