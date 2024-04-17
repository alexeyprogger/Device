package com.futuremed.pacient.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.futuremed.pacient.R
import com.futuremed.pacient.databinding.FragmentMapBinding


class MapFragment : Fragment() {

    lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.fragment = this

        return binding.root
    }

    fun openCarInfo() {
        MapDialog(requireContext(), DialogType.CAR)
    }

    fun openHospitalInfo() {
        MapDialog(requireContext(), DialogType.HOSPITAL)
    }
}