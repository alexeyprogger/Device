package com.futuremed.pacient.data.helper

import android.content.Context
import com.futuremed.pacient.data.model.*

class UserAccountHelper(context: Context) {

    private var preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    private var editor = preferences.edit()

    fun getUserAccount(): UserAccount {
        return UserAccount(
            name = getUserName().trim(),
            surname = getUserSurname().trim(),
            patronymic = getUserPatronymic().trim(),
            phoneNumber = getUserPhoneNumber().trim(),
            email = getUserEmail().trim(),
            birth = getUserBirth().trim(),
            password = getUserPassword().trim()
        )
    }

    fun saveUserAccount(userAccount: UserAccount) {
        saveUserName(userAccount.name)
        saveUserSurname(userAccount.surname)
        saveUserPatronymic(userAccount.patronymic)
        saveUserPhoneNumber(userAccount.phoneNumber)
        saveUserEmail(userAccount.email)
        saveUserBirth(userAccount.birth)
        saveUserPassword(userAccount.password)
    }

    private fun getUserName(): String {
        return preferences.getString(USER_NAME_KEY, "none").toString()
    }

    private fun getUserSurname(): String {
        return preferences.getString(USER_SURNAME_KEY, "none").toString()
    }

    private fun getUserPatronymic(): String {
        return preferences.getString(USER_PATRONYMIC_KEY, "none").toString()
    }

    private fun getUserPhoneNumber(): String {
        return preferences.getString(USER_PHONE_NUMBER_KEY, "none").toString()
    }

    private fun getUserEmail(): String {
        return preferences.getString(USER_EMAIL_KEY, "none").toString()
    }
    private fun getUserBirth(): String {
        return preferences.getString(USER_BIRTH_KEY, "none").toString()
    }

    private fun getUserPassword(): String {
        return preferences.getString(USER_PASSWORD_KEY, "none").toString()
    }

    private fun saveUserName(name: String) {
        editor.putString(USER_NAME_KEY, name).apply()
    }

    private fun saveUserSurname(surname: String) {
        editor.putString(USER_SURNAME_KEY, surname).apply()
    }

    private fun saveUserPatronymic(patronymic: String) {
        editor.putString(USER_PATRONYMIC_KEY, patronymic).apply()
    }

    private fun saveUserPhoneNumber(phoneNumber: String) {
        editor.putString(USER_PHONE_NUMBER_KEY, phoneNumber).apply()
    }

    private fun saveUserEmail(email: String) {
        editor.putString(USER_EMAIL_KEY, email).apply()
    }
    private fun saveUserBirth(birth: String) {
        editor.putString(USER_BIRTH_KEY, birth).apply()
    }

    private fun saveUserPassword(password: String) {
        editor.putString(USER_PASSWORD_KEY, password).apply()
    }
}