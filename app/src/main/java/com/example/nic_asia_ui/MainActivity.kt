package com.example.nic_asia_ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.app.KeyguardManager
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.locationalertdialog.*

class MainActivity : AppCompatActivity() {
    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback


        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("Authentication error: $errString")
                    startActivity(Intent(this@MainActivity,HomePage::class.java))
                    //this is for any particular activity
//                    overridePendingTransition(R.anim.slideleft,R.anim.slideright)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)


                    val dialog = Dialog(this@MainActivity)
                    dialog.setContentView(R.layout.dialog)
                    dialog.show()

                    val handler = Handler()
                    handler.postDelayed({
                        changeActivity(dialog)
                    },2000)
                }
            }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBiometricSupport()

        bottom_view.setOnNavigationItemSelectedListener {

            when(it.itemId)
            {
                R.id.location -> {

                    startActivity(Intent(this,forshow::class.java))
                    finish()

                }
                R.id.call ->
                {
                    val calDialog = Dialog(this)
                    calDialog.setContentView(R.layout.callayout)
                    calDialog.show()
                    calDialog.window?.setLayout(1200,600)

                }

            }
            true
        }



        btn_authenticate.setOnClickListener {
            val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("NICASIA MOBANK")
                .setSubtitle("Please put your finger on your fingerprint sensor in order to validate")
                .setDescription("Fingerprint Authentication")
                .setNegativeButton("Dismiss",
                    this.mainExecutor,
                    DialogInterface.OnClickListener { dialog, which ->
                    }).build()

            biometricPrompt.authenticate(getCancellationSignal(),
                mainExecutor,
                authenticationCallback)
        }

    }

    override fun onBackPressed() {

        val exit = AlertDialog.Builder(this)
        exit.setTitle("NIC MOBO")
        exit.setMessage("Are you sure you want to log out?")
        exit.setIcon(R.drawable.ic_baseline_warning_24)
        exit.setCancelable(false)
        exit.setPositiveButton("Yes")
        { _, _ ->
            finish()
        }
        exit.setNegativeButton("No")
        { _,_->
            Toast.makeText(this, "Thank you", Toast.LENGTH_LONG).show()
        }
        exit.show()

    }


    //mainActivity
    private fun changeActivity(dialog: Dialog) {

        dialog.dismiss()
        startActivity(Intent(this@MainActivity,HomePage::class.java))
        finish()
        notifyUser("Authentication Success!")
    }


    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport(): Boolean {
        val keyguardManager: KeyguardManager =
            getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isKeyguardSecure) {
            notifyUser("Fingerprint hs not been enabled in settings.")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED
        ) {
            notifyUser("Fingerprint hs not been enabled in settings.")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }

    fun doauthfirst(view: View) {
        Toast.makeText(this,"Please, first verify yourself  Bro", Toast.LENGTH_LONG).show()
    }
}