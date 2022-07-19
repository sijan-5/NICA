package com.example.nic_asia_ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.locationalertdialog.*

class forshow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forshow)

        var dialogg = Dialog(this)
        dialogg.setContentView(R.layout.locationalertdialog)
        dialogg.setCanceledOnTouchOutside(false)
        dialogg.show()

        dialogg.cancelLocation.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}