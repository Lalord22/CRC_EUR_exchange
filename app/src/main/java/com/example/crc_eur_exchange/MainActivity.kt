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

        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Calculate the converted amount and set it in etNameCR
                val inputText = s.toString()
                if (inputText.isNotEmpty()) {
                    val amountInEuros = inputText.toDouble()
                    val convertedAmountInColones = amountInEuros * conversionRate
                    etNameCR.setText(convertedAmountInColones.toString())
                } else {
                    // Clear etNameCR if etName is empty
                    etNameCR.text = null
                }
            }
        })

    }
}