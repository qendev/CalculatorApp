package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastNumeric:Boolean = false
    var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }

    fun clickButton(view: View) {
        binding.textViewInput.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun clearText(view: View) {
        binding.textViewInput.text = ""
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot){
            binding.textViewInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var textValue = binding.textViewInput.text.toString()
            var prefix = ""
            try {
                if (textValue.startsWith("-")){
                    prefix = "-"
                    textValue = textValue.substring(1)
                }
                when{
                    // If the inputValue contains the Subtraction operator
                    textValue.contains("-") ->{
                        // Will split the inputValue using Subtraction operator
                        val splitValue = textValue.split("-")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        binding.textViewInput.text =removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())



                    }

                    // If the inputValue contains the Addition operator
                    textValue.contains("+") ->{
                        // Will split the inputValue using Subtraction operator
                        val splitValue = textValue.split("+")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        binding.textViewInput.text =removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())



                    }

                    // If the inputValue contains the Multiplication operator
                    textValue.contains("*") ->{
                        // Will split the inputValue using Subtraction operator
                        val splitValue = textValue.split("*")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        binding.textViewInput.text =removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())



                    }

                    // If the inputValue contains the Division operator
                    textValue.contains("/") ->{
                        val splitValue = textValue.split("/")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        binding.textViewInput.text =removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())



                    }






                }


            }catch (e:ArithmeticException){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun onOperator(view: View){
        binding.textViewInput.text.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                binding.textViewInput.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }

        }
    }

    private fun removeZeroAfterDot(result:String):String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length - 2)
        return value

    }

    private  fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }


}