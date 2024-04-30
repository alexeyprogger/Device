package com.futuremed.pacient.data.model

import java.util.Date

const val STORAGE_NAME = "FUTURE_MED_APP_SETTINGS"
const val USER_NAME_KEY = "USER_NAME_KEY"
const val USER_SURNAME_KEY = "USER_SURNAME_KEY"
const val USER_PATRONYMIC_KEY = "USER_PATRONYMIC_KEY"
const val USER_PHONE_NUMBER_KEY = "USER_PHONE_NUMBER_KEY"
const val USER_EMAIL_KEY = "USER_EMAIL_KEY"
const val USER_BIRTH_KEY = "USER_BIRTH_KEY"
const val USER_PASSWORD_KEY = "USER_PASSWORD_KEY"

data class UserAccount(
    var name: String,
    var surname: String,
    var patronymic: String,
    var phoneNumber: String,
    var email: String,
    var birth: String,
    var password: String
) : java.io.Serializable
