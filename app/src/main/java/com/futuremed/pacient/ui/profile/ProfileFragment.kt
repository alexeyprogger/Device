package com.futuremed.pacient.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.futuremed.pacient.ui.medicalcard.MedicalCardFragment
import com.futuremed.pacient.R
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.databinding.FragmentProfileBinding
import kotlin.concurrent.fixedRateTimer


class ProfileFragment : Fragment() {

    var userTypeText: String = ""
    lateinit var binding: FragmentProfileBinding

    var userAccount: UserAccount = UserAccount(
        name = "",
        surname = "",
        patronymic = "",
        phoneNumber = "",
        email = "",
        password = ""
    )
    lateinit var helper: UserAccountHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        helper = UserAccountHelper(binding.root.context)

        userAccount = helper.getUserAccount()
        binding.fragment = this
        binding.userAccount = userAccount

        userTypeText = "Пациент"

        binding.invalidateAll()

        binding.executePendingBindings()
        return binding.root
    }


    fun openMedicalCard() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, MedicalCardFragment()).addToBackStack(null).commit()
    }

    fun close() {
        val fragmentManager = parentFragmentManager
        fragmentManager.popBackStack()
        if (userAccount.name.trim() != "") {
            helper.saveUserAccount(userAccount)
        }
    }
}