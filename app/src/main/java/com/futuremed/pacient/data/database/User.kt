package com.futuremed.pacient.data.database

import android.widget.EditText
import androidx.databinding.adapters.DatePickerBindingAdapter
import java.util.*


class User {
     lateinit var id: String
     lateinit var name: String
     lateinit var surname: String
     lateinit var partronymic: String
     lateinit var number: String
     lateinit var mail: String
    lateinit var birth: String
     constructor(){}
    constructor(id: String, name: String, surname: String, partronymic: String, number: String, birth: String, mail: String) {
        this.id = id
        this.name = name
        this.surname = surname
        this.partronymic = partronymic
        this.number = number
        this.birth = birth
        this.mail = mail
    }

}