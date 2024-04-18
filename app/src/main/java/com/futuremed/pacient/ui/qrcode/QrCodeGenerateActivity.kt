package com.futuremed.pacient.ui.qrcode

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.futuremed.pacient.R
import com.futuremed.pacient.data.helper.UserAccountHelper
import com.futuremed.pacient.data.model.UserAccount
import com.futuremed.pacient.databinding.ActivityQrCodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

class QrCodeGenerateActivity : AppCompatActivity() {

    private lateinit var userAccount: UserAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val helper = UserAccountHelper(this)

        userAccount = helper.getUserAccount()

        val binding =
            DataBindingUtil.setContentView<ActivityQrCodeBinding>(this, R.layout.activity_qr_code)
        setContentView(binding.root)
        binding.activity = this

        binding.qrCodeContainer.setImageBitmap(getQrCodeBitmap())

        val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))")
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31), ('Tata Tata', 45);")

        val query: Cursor = db.rawQuery("SELECT * FROM users;", null)
        val textView = findViewById<TextView>(R.id.textDB)
        textView.text = "";

        textView?.let {
            while (query.moveToNext()) {
                val name: String = query.getString(0)
                val age: Int = query.getInt(1)
                textView.append("Name: $name\n")
            }
            query.close()
            db.close()
        } ?: Log.e("MainActivity", "TextView is null")
    }

    //Генерация qr-кода
    private fun getQrCodeBitmap(): Bitmap {
        val size = 512 //pixels
        val qrCodeContent =
            "PatientInfo: ${userAccount.name} ${userAccount.surname} ${userAccount.patronymic} ${userAccount.phoneNumber}"
        val hints = hashMapOf<EncodeHintType, Any>().also {
            it[EncodeHintType.MARGIN] = 1
        } // Make the QR code buffer border narrower
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size, hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    fun back() {
        finish()
    }

}