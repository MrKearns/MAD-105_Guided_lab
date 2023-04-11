package com.example.mad_105_guided_lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    enum class LoginSucccess
        constructor(val intValu: Int){

            login(1),
            password(2),
            success(0)

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtLogin = findViewById<TextView>(R.id.idLoginText)
        val txtPassword = findViewById<TextView>(R.id.idPassword)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            when (CheckLogin(txtLogin.text.toString(), txtPassword.text.toString())){
                LoginSucccess.login -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessageLogin), Toast.LENGTH_LONG).show()
                    txtLogin.requestFocus()
                }

                LoginSucccess.password -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessagePassword), Toast.LENGTH_LONG).show()
                    txtLogin.requestFocus()
                }

                else -> {
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    fun CheckLogin (txtLogin: String, txtPassword: String) : LoginSucccess {
        val holdLogin = "Jonathan"
        val holdPass = "password"

        if (holdLogin != txtLogin) {
            return LoginSucccess.login
        }
        return if (holdPass != txtPassword) {
            return LoginSucccess.password
        } else LoginSucccess.success
    }
}