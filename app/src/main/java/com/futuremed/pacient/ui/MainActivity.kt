package com.futuremed.pacient.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.futuremed.pacient.R
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.ui.chats.ChatListFragment
import com.futuremed.pacient.ui.mainpage.MainPageFragment
import com.futuremed.pacient.ui.map.MapFragment
import com.futuremed.pacient.ui.more.MoreInformationFragment
import com.futuremed.pacient.ui.qrcode.QrCodeGenerateActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val mainPageFragment = MainPageFragment{openQrCode()}
        val mapFragment = MapFragment()

        val navigationView: BottomNavigationView =
            findViewById(R.id.bottom_navigation_main_activity)
        fragmentManager.beginTransaction().add(R.id.main_container, mainPageFragment)
            .commit()

        navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_navigation_button -> fragmentManager.beginTransaction()
                    .replace(R.id.main_container, mainPageFragment).commit()

                R.id.map_navigation_button -> fragmentManager.beginTransaction()
                    .replace(R.id.main_container, mapFragment).commit()

                R.id.chat_navigation_button -> fragmentManager.beginTransaction()
                    .replace(R.id.main_container, ChatListFragment()).commit()


                R.id.more_navigation_button -> fragmentManager.beginTransaction()
                    .replace(R.id.main_container, MoreInformationFragment()).commit()
            }
            return@setOnItemSelectedListener true
        }

    }
    private fun openQrCode() {
        startActivity(Intent(this, QrCodeGenerateActivity::class.java))
    }
}