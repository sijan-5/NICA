package com.example.nic_asia_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}