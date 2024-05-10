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
                val inputText = s.toString()
                if (isUserInputOnEtNameCR) {
                    if (inputText.isNotEmpty()) {
                        val amountInEuros = inputText.toIntOrNull() ?: 0
                        if (amountInEuros != 0){
                            val convertedAmountInColones = amountInEuros * conversionRate
                            isUserInputOnEtNameCR = false
                            etNameCR.setText(convertedAmountInColones.toString())
                        }
                    } else {
                        etNameCR.text = null
                    }
                } else {
                    isUserInputOnEtNameCR = true
                }
            }
        })

        // Flag to indicate whether text change in etName is caused by user input
        var isUserInputOnEtName = true

        etNameCR.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString()
                if (isUserInputOnEtName) {
                    if (inputText.isNotEmpty()) {
                        val amountInColones = inputText.toIntOrNull() ?: 0
                        if(amountInColones != 0){
                            val convertedAmountInEuros = amountInColones / conversionRate
                            isUserInputOnEtName = false
                            etName.setText(convertedAmountInEuros.toString())
                        }
                    } else {
                        etName.text = null
                    }
                } else {
                    isUserInputOnEtName = true
                }
            }
        })
    }




}