package com.futuremed.pacient.ui.medicalcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.futuremed.pacient.R
import com.futuremed.pacient.data.helper.MedicalCardHelper
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.data.model.MedicalCard
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.data.model.UserGender
import com.futuremed.pacient.databinding.FragmentMedicalCardBinding

class MedicalCardFragment(
) : Fragment() {

    var fio: String = ""
    lateinit var binding: FragmentMedicalCardBinding
    var userAccount: UserAccount = UserAccount(
        name = "",
        surname = "",
        patronymic = "",
        phoneNumber = "",
        email = "",
        password = ""
    )


    var medicalCard: MedicalCard =
        MedicalCard(bloodType = "1+", gender = UserGender.WOMAN)

    lateinit var medicalCardHelper: MedicalCardHelper
    lateinit var userAccountHelper: UserAccountHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_medical_card, container, false)

        medicalCardHelper = MedicalCardHelper(binding.root.context)
        userAccountHelper = UserAccountHelper(binding.root.context)

        userAccount = userAccountHelper.getUserAccount()

        setData()

        binding.fragment = this
        binding.medicalCard = medicalCard

        binding.invalidateAll()

        return binding.root
    }

    fun setFio() {
        val nameChar = userAccount.name[0]
        val patronymicChar = userAccount.patronymic[0]
        fio = "${userAccount.surname} ${nameChar}. ${patronymicChar}."
    }

    fun setData() {

        setFio()
        val medicalCard = medicalCardHelper.getMedicalCard()

        if (medicalCard.age != "none") {
            this.medicalCard = medicalCard
        }
        binding.invalidateAll()

    }

    fun openMyHealth() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, MyHealthFragment()).addToBackStack(null).commit()
    }

    fun openMyHistory() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, VisitHistoryFragment()).addToBackStack(null).commit()
    }

    fun close() {
        val fragmentManager = parentFragmentManager
        fragmentManager.popBackStack()
    }

    fun openAnalysis() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, AnalysisFragment()).addToBackStack(null).commit()
    }

    fun saveMedicalData() {
        medicalCardHelper.saveMedicalCard(medicalCard)
        Toast.makeText(binding.root.context, "Сохранено", Toast.LENGTH_SHORT).show()
    }
}