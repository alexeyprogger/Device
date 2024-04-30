package com.futuremed.pacient.ui.profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.futuremed.pacient.R
import com.futuremed.pacient.data.database.User
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.databinding.FragmentProfileBinding
import com.futuremed.pacient.ui.medicalcard.MedicalCardFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text
import java.util.*


class ProfileFragment : Fragment() {

    var userTypeText: String = ""
    lateinit var binding: FragmentProfileBinding

    var userAccount: UserAccount = UserAccount(
        name = "",
        surname = "",
        patronymic = "",
        phoneNumber = "",
        email = "",
        birth = "",
        password = ""
    )
    lateinit var helper: UserAccountHelper
    private lateinit var edName: EditText
    private lateinit var edSurname: EditText
    private lateinit var edOt: EditText
    private lateinit var edNumber: EditText
    private lateinit var edMail: EditText
    private lateinit var pickDateBtn: Button


    private lateinit var mDataBase: DatabaseReference
    private var USER_KEY = "User"
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
        init()
        return binding.root
    }
    private fun init() {
        edName = binding.root.findViewById(R.id.edName)
        edSurname = binding.root.findViewById(R.id.edSurname)
        edOt = binding.root.findViewById(R.id.edOt)
        edMail = binding.root.findViewById(R.id.edMail)
        edNumber = binding.root.findViewById(R.id.edNumber)
        pickDateBtn = binding.root.findViewById(R.id.birthButton)
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)

        pickDateBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                binding.root.context,
                { _, year, monthOfYear, dayOfMonth ->
                    pickDateBtn.text ="${String.format("%02d.%02d.%04d", dayOfMonth, month + 1, year)}"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    fun openMedicalCard() {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, MedicalCardFragment()).addToBackStack(null).commit()
    }

    @SuppressLint("RestrictedApi")
    fun close() {
        val fragmentManager = parentFragmentManager
        if (userAccount.name.trim() != "" && userAccount.surname.trim()!=""
            && userAccount.patronymic.trim()!="" && userAccount.phoneNumber.trim()!=""
            && userAccount.email.trim()!="") {
            helper.saveUserAccount(userAccount)
            var id = mDataBase.key.toString()
            var name = edName.text.toString()
            var surname = edSurname.text.toString()
            var partronymic = edOt.text.toString()
            var number = edNumber.text.toString()
            var email = edMail.text.toString()
            var birth = pickDateBtn.text.toString()


            val newUser = User(id, name, surname, partronymic, number, birth, email)
            mDataBase.push().setValue(newUser)
            fragmentManager.popBackStack()

        }
        else {
            Toast.makeText(binding.root.context,"Не должно быть пустых полей!", Toast.LENGTH_SHORT).show()}
    }
}