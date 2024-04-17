package com.futuremed.pacient.data.helper

import android.content.Context
import com.futuremed.pacient.data.model.*

class MedicalCardHelper(context: Context) {

    private var preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    private var editor = preferences.edit()

    fun getMedicalCard(): MedicalCard {
        return MedicalCard(
            age = getAge(),
            gender = getGender(),
            weight = getWeight(),
            height = getHeight(),
            bloodType = getBloodType()
        )
    }

    fun saveMedicalCard(medicalCard: MedicalCard) {
        saveAge(medicalCard.age)
        saveGender(medicalCard.gender.name)
        saveWeight(medicalCard.weight)
        saveHeight(medicalCard.height)
        saveBloodType(medicalCard.bloodType)
    }

    private fun getAge(): String {
        return preferences.getString(MEDICAL_CARD_AGE_KEY, "none").toString()
    }

    private fun getGender(): UserGender {
        return when (preferences.getString(MEDICAL_CARD_GENDER_KEY, "none")) {
            "MAN" -> UserGender.MAN
            "WOMAN" -> UserGender.WOMAN
            else -> UserGender.NONE
        }
    }

    private fun getWeight(): String {
        return preferences.getString(MEDICAL_CARD_WEIGHT_KEY, "none").toString()
    }

    private fun getHeight(): String {
        return preferences.getString(MEDICAL_CARD_HEIGHT_KEY, "none").toString()
    }

    private fun getBloodType(): String {
        return preferences.getString(MEDICAL_CARD_BLOOD_KEY, "none").toString()
    }

    private fun saveAge(age: String) {
        editor.putString(MEDICAL_CARD_AGE_KEY, age).apply()
    }

    private fun saveGender(gender: String) {
        editor.putString(MEDICAL_CARD_GENDER_KEY, gender).apply()
    }

    private fun saveWeight(weight: String) {
        editor.putString(MEDICAL_CARD_WEIGHT_KEY, weight).apply()
    }

    private fun saveHeight(height: String) {
        editor.putString(MEDICAL_CARD_HEIGHT_KEY, height).apply()
    }

    private fun saveBloodType(blood: String) {
        editor.putString(MEDICAL_CARD_BLOOD_KEY, blood).apply()
    }
}