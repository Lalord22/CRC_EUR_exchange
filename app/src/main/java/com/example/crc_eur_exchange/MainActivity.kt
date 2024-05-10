package com.example.crc_eur_exchange

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val conversionRate = 551

    private fun applyNumberInputFilter(editText: EditText) {
        val inputFilter = InputFilter { source, _, _, _, _, _ ->
            // Accept only digits (0-9)
            if (source.all { it.isDigit() || it == '-' }) null else ""
        }
        val inputFilters = arrayOf(inputFilter)
        editText.filters = inputFilters
        editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etName = findViewById<AppCompatEditText>(R.id.etName)
        val etNameCR = findViewById<AppCompatEditText>(R.id.etNameCR)

        applyNumberInputFilter(etName)
        applyNumberInputFilter(etNameCR)

        // Flag to indicate whether text change in etNameCR is caused by user input
        var isUserInputOnEtNameCR = true

        // Handler for debounce mechanism
        val debounceHandler = Handler(Looper.getMainLooper())

        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                debounceHandler.removeCallbacksAndMessages(null) // Remove previous callbacks

                debounceHandler.postDelayed({
                    val inputText = s.toString()
                    if (isUserInputOnEtNameCR) {
                        if (inputText.isNotEmpty()) {
                            val amountInEuros = inputText.toIntOrNull() ?: 0
                            val convertedAmountInColones = amountInEuros * conversionRate
                            isUserInputOnEtNameCR = false
                            etNameCR.setText(convertedAmountInColones.toString())
                            etNameCR.setSelection(etName.length())
                        } else {
                            etNameCR.setText("0")
                        }
                    } else {
                        isUserInputOnEtNameCR = true
                    }
                }, DEBOUNCE_DELAY_MS)
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
                debounceHandler.removeCallbacksAndMessages(null) // Remove previous callbacks

                debounceHandler.postDelayed({
                    val inputText = s.toString()
                    if (isUserInputOnEtName) {
                        if (inputText.isNotEmpty()) {
                            val amountInColones = inputText.toIntOrNull() ?: 0
                            val convertedAmountInEuros = amountInColones / conversionRate
                            isUserInputOnEtName = false
                            etName.setText(convertedAmountInEuros.toString())
                            etName.setSelection(etName.length())
                        } else {
                            etName.setText("0")
                        }
                    } else {
                        isUserInputOnEtName = true
                    }
                }, DEBOUNCE_DELAY_MS)
            }
        })
    }

    companion object {
        private const val DEBOUNCE_DELAY_MS = 300L // Adjust this delay as needed
    }





}