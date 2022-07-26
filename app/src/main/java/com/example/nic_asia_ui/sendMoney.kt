package com.example.nic_asia_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class sendMoney : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}