package com.futuremed.pacient.ui.map

import android.content.Context
import com.futuremed.pacient.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class MapDialog(
    private val context: Context,
    dialogType: DialogType
) : BottomSheetDialog(context) {

    private lateinit var dialog: BottomSheetDialog

    init {
        if (dialogType == DialogType.HOSPITAL) {
            openDialog("hospital")
        } else {
            openDialog("car")
        }
    }

    fun openDialog(type: String) {
        dialog = BottomSheetDialog(context)
        if (type == "hospital") dialog.setContentView(R.layout.hospital_info_layout)
        else dialog.setContentView(R.layout.car_info_layout)
        dialog.setOnCancelListener {
            dialog.cancel()
        }
        dialog.show()
    }
}