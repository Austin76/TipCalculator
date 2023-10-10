package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher

import com.example.tipcalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.baseEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                calculateTipAndTotal()
            }
        })

        binding.percentSlider.addOnChangeListener { slider, value, fromUser ->
            calculateTipAndTotal()
        }
    }

    private fun calculateTipAndTotal() {
        if (binding.baseEditText.text.isEmpty()) {
            binding.tipAmountTextView.text = "0.00"
            binding.totalAmountTextView.text = "0.00"
            return
        }

        val billAmount = binding.baseEditText.text.toString().toDouble()
        val tipPercent = binding.percentSlider.value * .01

        val tip = billAmount * tipPercent
        val total = billAmount + tip

        binding.tipAmountTextView.text = "%.2f".format(tip)
        binding.totalAmountTextView.text = "%.2f".format(total)
    }
}