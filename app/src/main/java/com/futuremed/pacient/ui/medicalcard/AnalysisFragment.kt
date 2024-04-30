package com.futuremed.pacient.ui.medicalcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import com.futuremed.pacient.R
import androidx.appcompat.app.AppCompatActivity


class AnalysisFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_analysis, container, false)
        val back = view.findViewById<ImageView>(R.id.back_btn)
        back.setOnClickListener {
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }
        return view
    }

}