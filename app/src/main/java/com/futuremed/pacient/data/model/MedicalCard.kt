package com.futuremed.pacient.data.model

const val MEDICAL_CARD_AGE_KEY = "MEDICAL_CARD_AGE_KEY"
const val MEDICAL_CARD_GENDER_KEY = "MEDICAL_CARD_GENDER_KEY"
const val MEDICAL_CARD_HEIGHT_KEY = "MEDICAL_CARD_HEIGHT_KEY"
const val MEDICAL_CARD_WEIGHT_KEY = "MEDICAL_CARD_WEIGHT_KEY"
const val MEDICAL_CARD_BLOOD_KEY = "MEDICAL_CARD_BLOOD_KEY"

data class MedicalCard(
    var age: String = "",
    var gender: UserGender,
    var height: String = "",
    var weight: String = "",
    var bloodType: String
)