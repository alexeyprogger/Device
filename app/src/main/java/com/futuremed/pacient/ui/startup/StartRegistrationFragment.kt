package com.futuremed.pacient.ui.startup

import android.R.attr.phoneNumber
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.futuremed.pacient.AppSettings
import com.futuremed.pacient.R
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.databinding.FragmentStartRegistrationBinding
import com.futuremed.pacient.ui.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit


class StartRegistrationFragment(
    private val onRegistered: () -> Unit
) : Fragment() {

    private lateinit var binding: FragmentStartRegistrationBinding


    private var mAuth: FirebaseAuth? = null
    private lateinit var edtPhone: EditText
    private lateinit var edtOTP: EditText
    private var verifyOTPBtn: Button? = null
    private var generateOTPBtn: Button? = null
    private var verificationId: String? = null

    private var TAG = "PhoneAuth"

    private var phoneVerificationId: String? = null
    private var mCallBack: OnVerificationStateChangedCallbacks? = null

    var userAccount = UserAccount(
        name = "",
        surname = "",
        patronymic = "",
        phoneNumber = "",
        email = "",
        birth = "",
        password = ""
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        //    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_start_registration,
//            container,
//            false
//        )
//
//        binding.fragment = this
//        binding.userAccount = userAccount
//
//        mAuth = FirebaseAuth.getInstance();
//        mainActivity = activity as MainActivity
//        edtPhone = binding.root.findViewById(R.id.idEdtPhoneNumber);
//        edtOTP = binding.root.findViewById(R.id.idEdtOtp);
//        verifyOTPBtn = binding.root.findViewById(R.id.idBtnVerify);
//        generateOTPBtn = binding.root.findViewById(R.id.idBtnGetOtp);
//        return binding.root
//    }

    }
    fun sendCode() {
        edtPhone = binding.root.findViewById(R.id.idEdtPhoneNumber);
        setUpVerificatonCallbacks();
        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(edtPhone.text.toString()) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireContext() as Activity) // Activity (for callback binding)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // Сохраняем идентификатор подтверждения, чтобы использовать его позже при проверке кода
                this@StartRegistrationFragment.verificationId = verificationId
            }
        })
            .build()
        Toast.makeText(context, "${edtPhone?.text.toString()}", Toast.LENGTH_SHORT).show()
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    fun setUpVerificatonCallbacks() {
        mCallBack = object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(
                credential: PhoneAuthCredential
            ) {
                verifyOTPBtn?.isEnabled = false
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.d(
                        TAG, "Invalid credential: "
                                + e.getLocalizedMessage()
                    )
                } else if (e is FirebaseTooManyRequestsException) {
                    // SMS quota exceeded
                    Log.d(TAG, "SMS Quota exceeded.")
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: ForceResendingToken
            ) {
                phoneVerificationId = verificationId
                verifyOTPBtn?.isEnabled = true
                generateOTPBtn?.isEnabled = false
            }
        }
    }

    fun verifyCode() {
        edtOTP = binding.root.findViewById(R.id.idEdtOtp);
        val code: String = edtOTP?.text.toString()
        Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
        val credential = PhoneAuthProvider.getCredential(phoneVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(
                MainActivity()
            ) { task ->
                if (task.isSuccessful) {
                    verifyOTPBtn?.isEnabled = false
                    val user: FirebaseUser? = task.result.user
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }
    fun setClientUserType() {
        binding.userTypeChoice.root.visibility = View.GONE
        binding.userRegistrationLayout.root.visibility = View.VISIBLE
        binding.invalidateAll()
    }
//
//    fun birthday_fun(){
//        pickDateBtn = binding.root.findViewById(R.id.user_birth_text)
//            val c = Calendar.getInstance()
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)
//            val datePickerDialog = DatePickerDialog(
//                binding.root.context,
//                { _, year, monthOfYear, dayOfMonth ->
//                    pickDateBtn.text =
//                        "${String.format("%02d.%02d.%04d", dayOfMonth, month + 1, year)}"
//                },
//                year,
//                month,
//                day
//            )
//            datePickerDialog.show()
//
//    }
//    fun createAccountPassword() {
//        if(binding.userRegistrationLayout.userNameText.text.toString().trim()==""
//            || binding.userRegistrationLayout.userSurnameText.text.toString().trim()==""
//            || binding.userRegistrationLayout.userPartText.text.toString().trim()==""
//            || binding.userRegistrationLayout.userNumberText.text.toString().trim()==""
//            || binding.userRegistrationLayout.userMailText.text.toString().trim()==""
//            || binding.userRegistrationLayout.userBirthText.text.toString().trim()==""){
//            Toast.makeText(binding.root.context,"Все поля необходимо заполнить!", Toast.LENGTH_SHORT).show()
//        }
//        else {
//            binding.userRegistrationLayout.root.visibility = View.GONE
//            binding.userPasswordLayout.root.visibility = View.VISIBLE
//            binding.invalidateAll()
//        }
//    }
//
    fun completeRegistration() {
        val password = binding.userPasswordLayout.userPasswordText.text.toString().trim()
        val repeatPassword =  binding.userPasswordLayout.userRepeatPasswordText.text.toString().trim()
        if(password == ""){
            Toast.makeText(binding.root.context,"Введите пароль!", Toast.LENGTH_SHORT).show()
        }
        else if(password != repeatPassword){
            Toast.makeText(binding.root.context,"Пароли не совпадают!", Toast.LENGTH_SHORT).show()
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
//
    private fun saveData() {
        val helper = UserAccountHelper(binding.root.context)
        helper.saveUserAccount(userAccount)
    }
//
//    fun backToStart() {
//        binding.userRegistrationLayout.root.visibility = View.GONE
//        binding.userTypeChoice.root.visibility = View.VISIBLE
//        binding.invalidateAll()
//    }
//
//    fun backToRegistration() {
//        binding.userPasswordLayout.root.visibility = View.GONE
//        binding.userRegistrationLayout.root.visibility = View.VISIBLE
//        binding.invalidateAll()
//    }
//
//    fun openDoctorDialog(){
//        val builder = AlertDialog.Builder(context)
//        builder.setMessage("Для регистрации аккаунта врача \n" +
//                "установите \"FutureMed Доктор\"  \nНачать скачивание?")
//        builder.setNegativeButton("Отмена", DialogInterface.OnClickListener { dialog, which ->
//            dialog.cancel()
//        })
//        builder.setPositiveButton("Скачать", DialogInterface.OnClickListener { dialog, which ->
//
//        })
//        val alertDialog = builder.create()
//        alertDialog.show()
//
//    }

}