package com.futuremed.pacient.ui.startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.futuremed.pacient.AppSettings
import com.futuremed.pacient.R
import com.futuremed.pacient.ui.MainActivity

class StartActivity : AppCompatActivity() {

    lateinit var appSettings: AppSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        appSettings = AppSettings(this)

        if (appSettings.isFirstStart()) {

            val startRegistrationFragment = StartRegistrationFragment() { openMainActivity() }
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, startRegistrationFragment).commit()

        } else {
            openMainActivity()
        }
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}