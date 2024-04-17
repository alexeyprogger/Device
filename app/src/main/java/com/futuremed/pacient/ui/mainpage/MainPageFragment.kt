package com.futuremed.pacient.ui.mainpage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.futuremed.pacient.R
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.databinding.FragmentMainPageBinding
import com.futuremed.pacient.ui.medicalcard.MedicalCardFragment
import com.futuremed.pacient.ui.profile.ProfileFragment
import com.futuremed.pacient.ui.recording.OnlineFragment

class MainPageFragment (
    private val openQrCodeGenerator: () -> Unit,
) : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    var title = ""
    var pacientInfo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)
        binding.fragment = this
        val helper = UserAccountHelper(binding.root.context)

        val userAccount = helper.getUserAccount()
        title = "Добрый день, ${userAccount.name}"
        pacientInfo = "${userAccount.surname} ${userAccount.name} \n25 лет, муж."
        binding.fragment = this

        return binding.root
    }

    fun makeRecordToDoctor() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, OnlineFragment()).addToBackStack(null).commit()
    }

    fun generateQrCode() {
        openQrCodeGenerator()
    }

    fun openProfile() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, ProfileFragment()).addToBackStack(null).commit()
    }

    fun openMedicalcard() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, MedicalCardFragment()).addToBackStack(null).commit()
    }

    fun callHelp() {
        val number = Uri.parse("tel:112")
        val intent = Intent(Intent.ACTION_DIAL, number)
        startActivity(intent)
    }


    fun makeOnlineConsultation() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, OnlineFragment()).addToBackStack(null).commit()
    }
}