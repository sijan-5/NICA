package com.example.nic_asia_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_fixdepo.*

class fixdepo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixdepo)


        val tenuree = resources.getStringArray(R.array.tenure)
        val pickacc = resources.getStringArray(R.array.acc_num)

        val arrayAdapterone = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,tenuree)
        val arrayAdaptertwo = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,pickacc)

        autoCompleteMonths.setAdapter(arrayAdapterone)
        autocompleteAccount.setAdapter(arrayAdaptertwo)

        tvcancel.setOnClickListener {
            startActivity(Intent(this,HomePage::class.java))
            finish()
        }
    }

}