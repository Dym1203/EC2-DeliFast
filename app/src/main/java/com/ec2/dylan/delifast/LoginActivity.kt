package com.ec2.dylan.delifast

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ec2.dylan.delifast.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val edtemail = binding.edtEmail.text.toString().trim()
            val edtpassword = binding.edtPassword.text.toString().trim()
            if (validateEmailPassword(edtemail, edtpassword)) {
                if (edtemail.equals("ejemplo@idat.edu.pe") && edtpassword.equals("123456")) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("¡Error!")
                        .setContentText("¡No existe el usuario!")
                        .setConfirmText("Listo")
                        .show()
                }
            }
            else {
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("¡Error!")
                        .setContentText("¡Los campos no cumplen con la validación!")
                        .setConfirmText("Listo")
                        .show()
            }
        }
    }

    private fun validateEmailPassword(email: String, password: String): Boolean {
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty() && password.length == 6
        return isEmailValid && isPasswordValid
    }

    override fun onBackPressed() {
        SweetAlertDialog(
            this,
            SweetAlertDialog.WARNING_TYPE
        ).setTitleText("¡Has oprimido el botón atrás!")
            .setContentText("¿Quieres cerrar la aplicación?")
            .setCancelText("Cancelar").setConfirmText("Cerrar")
            .showCancelButton(true).setCancelClickListener { sDialog: SweetAlertDialog ->
                sDialog.dismissWithAnimation()
                SweetAlertDialog(
                    this,
                    SweetAlertDialog.ERROR_TYPE
                ).setTitleText("¡Operación Cancelada!")
                    .setContentText("¡No saliste de la aplicación!")
                    .show()
            }.setConfirmClickListener { sweetAlertDialog: SweetAlertDialog ->
                sweetAlertDialog.dismissWithAnimation()
                System.exit(0)
            }.show()
    }

}