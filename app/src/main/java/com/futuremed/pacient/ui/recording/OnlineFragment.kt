package com.futuremed.pacient.ui.recording

import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.futuremed.pacient.R


class OnlineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_online, container, false)
        val recordBtn = view.findViewById<AppCompatButton>(R.id.record_button)

        val backBtn = view.findViewById<ImageView>(R.id.back_btn)

        recordBtn.setOnClickListener {
            Toast.makeText(view.context,"Вы записаны на прием", Toast.LENGTH_SHORT).show()
            back()
        }

        backBtn.setOnClickListener {
            back()
        }

        return view
    }

    fun back(){
        val fragmentManager = parentFragmentManager
        fragmentManager.popBackStack()
    }

}