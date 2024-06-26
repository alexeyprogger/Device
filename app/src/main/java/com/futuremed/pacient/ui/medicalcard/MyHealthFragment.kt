package com.futuremed.pacient.ui.medicalcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.futuremed.pacient.R


class MyHealthFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_health, container, false)

        val back = view.findViewById<ImageView>(R.id.back_btn)
        back.setOnClickListener {

            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }

        return view
    }

}