package com.example.nic_asia_ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        toggle = ActionBarDrawerToggle(this@HomePage, drawyer_layout, toolbar,
            R.string.open_account, R.string.invoice_history)


        drawyer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //always enable to appear the drawer icon on the action bar
        if(supportActionBar !=null) {

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_lock_24)

        }

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.notification -> Toast.makeText(this,"how are you notification",
                    Toast.LENGTH_LONG).show()

            }
            true
        }

        logout.setOnClickListener {

            showAlert()

        }


    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nic Mobank")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("yes")
        { _,_ ->
            val dd = Dialog(this)
            dd.setContentView(R.layout.logoutdialog)
            dd.show()

            val handler = Handler()
            handler.postDelayed({
                startActivity(Intent(this@HomePage,MainActivity::class.java))
                dd.dismiss()
                Toast.makeText(this,"You are logged out successfully",
                    Toast.LENGTH_LONG).show()
            },2000)


        }

        builder.setNegativeButton("No")
        { _,_ ->

        }
        builder.show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun openAccount(view: View) {
        startActivity(Intent(this,acc::class.java))


    }

    fun paymentmethod(view: View) {

        startActivity(Intent(this,Payment::class.java))


    }

    fun remm(view: View) {

        startActivity(Intent(this,Remittence::class.java))


    }

    fun fixedDepo(view: View) {
        startActivity(Intent(this,fixdepo::class.java))


    }

    fun sendingMethod(view: View) {
        startActivity(Intent(this,sendMoney::class.java))

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}