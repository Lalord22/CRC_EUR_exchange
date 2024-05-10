package com.example.crc_eur_exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val conversionRate = 551
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etName = findViewById<AppCompatEditText>(R.id.etName)
        val etNameCR = findViewById<AppCompatEditText>(R.id.etNameCR)

        // Flag to indicate whether text change in etNameCR is caused by user input
        var isUserInputOnEtNameCR = true

        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                if (isUserInputOnEtNameCR) {
                    val inputText = s.toString()
                    if (inputText.isNotEmpty()) {
                        val amountInEuros = inputText.toDouble()
                        val convertedAmountInColones = amountInEuros * conversionRate
                        isUserInputOnEtNameCR = false
                        etNameCR.setText(convertedAmountInColones.toString())
                    } else {
                        etNameCR.text = null
                    }
                } else {
                    isUserInputOnEtNameCR = true
                }
            }
        })

        etNameCR.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                if (isUserInputOnEtNameCR) {
                    val inputText = s.toString()
                    if (inputText.isNotEmpty()) {
                        val amountInColones = inputText.toDouble()
                        val convertedAmountInEuros = amountInColones / conversionRate
                        isUserInputOnEtNameCR = false
                        etName.setText(convertedAmountInEuros.toString())
                    } else {
                        etName.text = null
                    }
                } else {
                    isUserInputOnEtNameCR = true
                }
            }
        })
    }

}