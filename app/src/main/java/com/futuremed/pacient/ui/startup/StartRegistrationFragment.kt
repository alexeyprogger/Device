package com.futuremed.pacient.ui.startup

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.futuremed.pacient.AppSettings
import com.futuremed.pacient.R
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.databinding.FragmentStartRegistrationBinding


class StartRegistrationFragment(
    private val onRegistered: () -> Unit
) : Fragment() {


    var userTypeText: String = ""
    private lateinit var binding: FragmentStartRegistrationBinding

    var userAccount = UserAccount(
        name = "",
        surname = "",
        patronymic = "",
        phoneNumber = "",
        email = "",
        password = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start_registration,
            container,
            false
        )
        binding.fragment = this
        binding.userAccount = userAccount


        return binding.root
    }

    fun setClientUserType() {
        userTypeText = "Пациент"
        binding.userTypeChoice.root.visibility = View.GONE
        binding.userRegistrationLayout.root.visibility = View.VISIBLE
        binding.invalidateAll()
    }

    fun createAccountPassword() {
        if(binding.userRegistrationLayout.userNameText.text.toString().trim()==""
            || binding.userRegistrationLayout.userSurnameText.text.toString().trim()==""
            || binding.userRegistrationLayout.userPartText.text.toString().trim()==""
            || binding.userRegistrationLayout.userNumberText.text.toString().trim()==""
            || binding.userRegistrationLayout.userMailText.text.toString().trim()==""){
            Toast.makeText(binding.root.context,"Все поля необходимо заполнить!", Toast.LENGTH_SHORT).show()
        }
        else {
            binding.userRegistrationLayout.root.visibility = View.GONE
            binding.userPasswordLayout.root.visibility = View.VISIBLE
            binding.invalidateAll()
        }
    }

    fun completeRegistration() {
        if(binding.userPasswordLayout.userPasswordText.text.toString().trim() == ""){
            Toast.makeText(binding.root.context,"Введите пароль!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(binding.root.context,"Регистрация успешно завершена!", Toast.LENGTH_SHORT).show()
            binding.userPasswordLayout.root.visibility = View.GONE
            binding.invalidateAll()
            val appSettings = AppSettings(binding.root.context)
            appSettings.changeFirstStart()
            saveData()
            onRegistered()
        }
    }

    private fun saveData() {
        val helper = UserAccountHelper(binding.root.context)
        helper.saveUserAccount(userAccount)
    }

    fun backToStart() {
        binding.userRegistrationLayout.root.visibility = View.GONE
        binding.userTypeChoice.root.visibility = View.VISIBLE
        binding.invalidateAll()
    }

    fun backToRegistration() {
        binding.userPasswordLayout.root.visibility = View.GONE
        binding.userRegistrationLayout.root.visibility = View.VISIBLE
        binding.invalidateAll()
    }

    fun openDoctorDialog(){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Для регистрации аккаунта врача \n" +
                "установите \"FutureMed Доктор\"  \nНачать скачивание?")
        builder.setNegativeButton("Отмена", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        builder.setPositiveButton("Скачать", DialogInterface.OnClickListener { dialog, which ->

        })
        val alertDialog = builder.create()
        alertDialog.show()

    }

}