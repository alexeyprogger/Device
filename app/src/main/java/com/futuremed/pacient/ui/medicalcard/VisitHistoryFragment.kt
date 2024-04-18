package com.futuremed.pacient.ui.medicalcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.futuremed.pacient.R


class VisitHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vizit_history, container, false)

        val recordBtn = view.findViewById<AppCompatButton>(R.id.record_button)

        val back = view.findViewById<ImageView>(R.id.back_btn)

        recordBtn.setOnClickListener {
            Toast.makeText(view.context,"Вы записаны на приём повторно", Toast.LENGTH_SHORT).show()
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }
        back.setOnClickListener {
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }
        // Inflate the layout for this fragment
        return view
    }

}