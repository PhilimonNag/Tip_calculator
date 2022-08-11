package com.philimonnag.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.philimonnag.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.jvm.internal.Intrinsics

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField=binding.plainTextInput.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost==null || cost==0.0){
            display(0.0)
            return
        }
        val tipPercentage=when(binding.tipOption.checkedRadioButtonId){
            R.id.option_twenty_percent->0.20
            R.id.option_eighteen_percent->0.18
            else->0.15
        }
        var tip=tipPercentage*cost
        if (binding.roundupSwitch.isChecked){
            tip=kotlin.math.ceil(tip)
            display(tip)
        }


    }
    private fun display(tip:Double){
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount,formattedTip)
    }
}