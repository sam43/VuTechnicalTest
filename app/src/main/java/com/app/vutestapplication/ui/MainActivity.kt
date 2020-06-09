package com.app.vutestapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.vutestapplication.R
import com.app.vutestapplication.ui.userinfo.UserInfoFragment

class MainActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
