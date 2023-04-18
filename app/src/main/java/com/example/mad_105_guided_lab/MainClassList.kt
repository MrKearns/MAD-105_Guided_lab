package com.example.mad_105_guided_lab

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.view.View
import kotlinx.coroutines.newSingleThreadContext

class MainClassList : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_class_list)

        val swDegreeCert = findViewById<Switch>(R.id.swDegreeCert)
        val spnDegree = findViewById<Spinner>(R.id.majorSpinner)
        val spnCert = findViewById<Spinner>(R.id.certSpinner)
        val txtCertificate = findViewById<TextView>(R.id.certLblUnderSpinner)
        val txtDegree = findViewById<TextView>(R.id.majorLbl)
        val btnNext = findViewById<Button>(R.id.nextButton)

        val firstName = findViewById<EditText>(R.id.firstName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val phone = findViewById<EditText>(R.id.phoneNumber)

        val spMonth = findViewById<Spinner>(R.id.monthSpinner)
        val txtDay = findViewById<EditText>(R.id.DOBDayTxt)
        val txtYear = findViewById<EditText>(R.id.DOBYearTxt)

        firstName.requestFocus()

        swDegreeCert.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                spnDegree.visibility = View.VISIBLE
                txtDegree.visibility = View.VISIBLE
                spnCert.visibility = View.GONE
                txtCertificate.visibility = View.GONE
            } else{
                spnDegree.visibility = View.GONE
                txtDegree.visibility = View.GONE
                spnCert.visibility = View.VISIBLE
                txtCertificate.visibility = View.VISIBLE
            }
        }

        btnNext.setOnClickListener {
            if (checkData()){
                var doBirth = ""
                doBirth = spMonth.selectedItem.toString() + "/" + txtDay.text.toString() + "/" + txtYear.text.toString()

                val nextScreen = Intent(this@MainClassList, ChooseClass::class.java)
                nextScreen.putExtra("FirstName", firstName.text.toString())
                nextScreen.putExtra("LastName", lastName.text.toString())
                nextScreen.putExtra("Phone", phone.text.toString())
                nextScreen.putExtra("BirthDate", doBirth)

                if(spnDegree.visibility == View.VISIBLE){
                    nextScreen.putExtra("isDegreeCert", "Degree")
                    nextScreen.putExtra("degreeCert", spnDegree.selectedItem.toString())
                } else {
                    nextScreen.putExtra("isDegreeCert", "Certificate")
                    nextScreen.putExtra("degreeCert", spnCert.selectedItem.toString())
                }

                startActivity(nextScreen)

            }

        }

    }

    private fun checkData(): Boolean {
        val firstName = findViewById<EditText>(R.id.firstName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val phone = findViewById<EditText>(R.id.phoneNumber)
        val txtDay = findViewById<EditText>(R.id.DOBDayTxt)
        val txtYear = findViewById<EditText>(R.id.DOBYearTxt)

        if(firstName.text.toString().isEmpty()){
            firstName.error = "Invalid First Name"
            firstName.requestFocus()
            return false
        }

        if(lastName.text.toString().isEmpty()){
            lastName.error = "Invalid Last Name"
            lastName.requestFocus()
            return false
        }

        if(phone.text.toString().isEmpty()){
            phone.error = "Invalid Phone Number"
            phone.requestFocus()
            return false
        }

        if(txtDay.text.toString().isEmpty()){
            txtDay.error = "Invalid Date Selection"
            txtDay.requestFocus()
            return false
        }

        if(txtYear.text.toString().isEmpty()){
            txtYear.error = "Invalid Date Selection"
            txtYear.requestFocus()
            return false
        }

        return true
    }
}