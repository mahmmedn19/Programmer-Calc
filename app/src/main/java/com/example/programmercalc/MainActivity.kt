/**
 * Author: "Mohamed Naser"
 * Date: 11/3/2023
 */

package com.example.programmercalc
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.math.BigInteger
import java.util.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private val binaryRegex = "[01]+".toRegex()
    private val octalRegex = "[0-7]+".toRegex()
    private val decimalRegex = "\\d+".toRegex()
    private val hexRegex = "[\\da-fA-F]+".toRegex()

    private val conversionTypes = arrayOf("Select Type ","Binary", "Octal", "Decimal", "Hexa")

    private lateinit var conversionTypeSpinner: Spinner
    private lateinit var binRes: TextView
    private lateinit var octRes: TextView
    private lateinit var decRes: TextView
    private lateinit var hexRes: TextView
    private lateinit var textBinary: TextView
    private lateinit var textOctal: TextView
    private lateinit var textDecimal: TextView
    private lateinit var textHexa: TextView
    private lateinit var input: TextView
    private lateinit var  clearBtn: Button
    private lateinit var rmBtn: Button
    private lateinit var eBtn: Button
    private lateinit var fBtn: Button
    private lateinit var aBtn: Button
    private lateinit var bBtn: Button
    private lateinit var cBtn: Button
    private lateinit var dBtn: Button
    private lateinit var zeroBtn: Button
    private lateinit var oneBtn: Button
    private lateinit var twoBtn: Button
    private lateinit var threeBtn: Button
    private lateinit var fourBtn: Button
    private lateinit var fiveBtn: Button
    private lateinit var sixBtn: Button
    private lateinit var sevenBtn: Button
    private lateinit var eightBtn: Button
    private lateinit var nineBtn: Button
    private lateinit var equalBtn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()

        setupEqualBtn()

        setupConversionTypeListener()

        setupNumberButtons()

        setupRemoveButton()

        setupClearButton()


    }

    private fun setupViews() {
        conversionTypeSpinner = findViewById(R.id.conversion_type_spinner)
        binRes = findViewById(R.id.bin_res)
        octRes = findViewById(R.id.oct_res)
        decRes = findViewById(R.id.dec_res)
        hexRes = findViewById(R.id.hex_res)
        input = findViewById(R.id.text_to_append_number)
        clearBtn = findViewById(R.id.clear_btn)
        rmBtn = findViewById(R.id.rm_btn)
        eBtn = findViewById(R.id.e_btn)
        fBtn = findViewById(R.id.f_btn)
        aBtn = findViewById(R.id.a_btn)
        bBtn = findViewById(R.id.b_btn)
        cBtn = findViewById(R.id.c_btn)
        dBtn = findViewById(R.id.d_btn)
        zeroBtn = findViewById(R.id.zero_btn)
        oneBtn = findViewById(R.id.one_btn)
        twoBtn = findViewById(R.id.two_btn)
        threeBtn = findViewById(R.id.three_btn)
        fourBtn = findViewById(R.id.four_btn)
        fiveBtn = findViewById(R.id.five_btn)
        sixBtn = findViewById(R.id.six_btn)
        sevenBtn = findViewById(R.id.seven_btn)
        eightBtn = findViewById(R.id.eight_btn)
        nineBtn = findViewById(R.id.nine_btn)
        equalBtn = findViewById(R.id.equal_btn)
        textBinary = findViewById(R.id.text_binary)
        textOctal = findViewById(R.id.text_octal)
        textDecimal = findViewById(R.id.text_decimal)
        textHexa = findViewById(R.id.text_hexa)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conversionTypeSpinner.adapter = adapter
    }

    private fun setupNumberButtons() {
        val numberButtons = listOf(
            zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, aBtn, bBtn, cBtn, dBtn, eBtn, fBtn
        )

        numberButtons.forEach { button ->
            button.setOnClickListener { appendText(button.text.toString()) }
        }
    }
    private fun setupClearButton() {
        clearBtn.setOnClickListener {
            binRes.text = ""
            octRes.text = ""
            decRes.text = ""
            hexRes.text = ""
            input.text= ""
        }
    }
    private fun setupRemoveButton() {
        rmBtn.setOnClickListener {
            when {

                input.text.isNotEmpty() -> input.text = input.text.dropLast(1)
            }
        }
    }

    // the btn not working yet and i complete this ):

     private fun setupEqualBtn() {
          // Set up the "Equal" button click listener
          equalBtn.setOnClickListener {
              val input = getInputText()
              val conversionType = conversionTypes[conversionTypeSpinner.selectedItemPosition]
              // Perform the conversion based on the selected conversion type
              when (conversionType) {
                  "Binary" -> {
                      if (binaryRegex.matches(input)) {
                          binRes.text = input
                          octRes.text = binToOct(input)
                          decRes.text = binToDec(input)
                          hexRes.text = binToHex(input)
                      } else {
                          binRes.text = "not valid"
                      }
                  }
                  "Octal" -> {
                      if (octalRegex.matches(input)) {
                          binRes.text = octToBin(input)
                          octRes.text = input
                          decRes.text = octToDec(input)
                          hexRes.text = octToHex(input)
                      } else {
                          octRes.text = "not valid"
                      }
                  }
                  "Decimal" -> {
                      if (decimalRegex.matches(input)) {
                          binRes.text = decToBin(input)
                          octRes.text = decToOct(input)
                          decRes.text = input
                          hexRes.text = decToHex(input)
                      } else {
                          decRes.text = "not valid"
                      }
                  }
                  "Hexa" -> {
                      if (hexRegex.matches(input)) {
                          binRes.text = hexToBin(input)
                          octRes.text = hexToOct(input)
                          decRes.text = hexToDec(input)
                          hexRes.text = input.uppercase(Locale.getDefault())
                      } else {
                          hexRes.text = "not valid"
                      }
                  }
              }
          }
      }
    private fun setupConversionTypeListener() {
        conversionTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                hideButtonsForConversionType(conversionTypes[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    private fun hideButtonsForConversionType(conversionType: String) {
        when (conversionType) {
            "Binary" -> {
                zeroBtn.isEnabled = true
                oneBtn.isEnabled = true
                twoBtn.isEnabled = false
                threeBtn.isEnabled = false
                fourBtn.isEnabled = false
                fiveBtn.isEnabled = false
                sixBtn.isEnabled = false
                sevenBtn.isEnabled = false
                eightBtn.isEnabled = false
                nineBtn.isEnabled = false
                aBtn.isEnabled = false
                bBtn.isEnabled = false
                cBtn.isEnabled = false
                dBtn.isEnabled = false
                eBtn.isEnabled = false
                fBtn.isEnabled = false
                textBinary.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_500))
                textOctal.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textDecimal.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textHexa.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                binRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_200))
                octRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                decRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                hexRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
            }
            "Octal" -> {
                zeroBtn.isEnabled = true
                oneBtn.isEnabled = true
                twoBtn.isEnabled = true
                threeBtn.isEnabled = true
                fourBtn.isEnabled = true
                fiveBtn.isEnabled = true
                sixBtn.isEnabled = true
                sevenBtn.isEnabled = true
                eightBtn.isEnabled = false
                nineBtn.isEnabled = false
                aBtn.isEnabled = false
                bBtn.isEnabled = false
                cBtn.isEnabled = false
                dBtn.isEnabled = false
                eBtn.isEnabled = false
                fBtn.isEnabled = false
                textBinary.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textOctal.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_500))
                textDecimal.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textHexa.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                binRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                octRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_200))
                decRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                hexRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
            }
            "Decimal" -> {
                zeroBtn.isEnabled = true
                oneBtn.isEnabled = true
                twoBtn.isEnabled = true
                threeBtn.isEnabled = true
                fourBtn.isEnabled = true
                fiveBtn.isEnabled = true
                sixBtn.isEnabled = true
                sevenBtn.isEnabled = true
                eightBtn.isEnabled = true
                nineBtn.isEnabled = true
                aBtn.isEnabled = false
                bBtn.isEnabled = false
                cBtn.isEnabled = false
                dBtn.isEnabled = false
                eBtn.isEnabled = false
                fBtn.isEnabled = false
                textBinary.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textOctal.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textDecimal.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_500))
                textHexa.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                binRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                octRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                decRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_200))
                hexRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
            }
            "Hexa" -> {
                zeroBtn.isEnabled = true
                oneBtn.isEnabled = true
                twoBtn.isEnabled = true
                threeBtn.isEnabled = true
                fourBtn.isEnabled = true
                fiveBtn.isEnabled = true
                sixBtn.isEnabled = true
                sevenBtn.isEnabled = true
                eightBtn.isEnabled = true
                nineBtn.isEnabled = true
                aBtn.isEnabled = true
                bBtn.isEnabled = true
                cBtn.isEnabled = true
                dBtn.isEnabled = true
                eBtn.isEnabled = true
                fBtn.isEnabled = true
                textBinary.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textOctal.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textDecimal.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_light))
                textHexa.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_500))
                binRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                octRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                decRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.gray_200))
                hexRes.setTextColor(ContextCompat.getColor(applicationContext,R.color.yellow_200))
            }


        }
    }

    private fun getInputText() = input.text.toString()

    private fun appendText(text: String) {
        val currentInput = input.text.toString()
        input.text = currentInput + text
    }
    private fun binToOct(input: String): String {
        // Pad the input with zeros to make its length a multiple of 3
        val paddedInput = input.padStart((input.length / 3 + 1) * 3, '0')

        // Convert the input to octal by grouping its digits into groups of 3, and converting each group to its octal equivalent
        val octalDigits = paddedInput.chunked(3) { chunk ->
            chunk.map { it.toString().toInt() }.fold(0) { acc, digit ->
                acc * 2 + digit
            }.toString(8)
        }

        // Join the octal digits together to form the final octal number
        return octalDigits.joinToString("").trimStart('0')
    }

    private fun binToDec(binary: String): String {
        var decimal = BigInteger.ZERO
        var power = BigInteger.ZERO
        for (i in binary.length - 1 downTo 0) {
            val digit = binary[i].toString().toInt()
            decimal += BigInteger.valueOf(digit.toLong()).multiply(BigInteger.valueOf(2).pow(power.toInt()))
            power++
        }
        return StringBuilder(decimal.toString()).toString()
    }
    private fun binToHex(binary: String): String {
        val decimal = BigInteger(binary, 2)
        return StringBuilder(decimal.toString(16).uppercase()).toString()
    }
    private fun octToBin(octal: String): String {
        val decimal = octToDec(octal)
        return decToBin(decimal)
    }

    private fun octToDec(octal: String): String {
        return try {
            BigInteger(octal, 8).toString()
        } catch (e: Exception) {
            ""
        }
    }
    private fun octToHex(oct: String): String {
        val decimal = octToDec(oct)
        return decToHex(decimal)
    }
    private fun decToBin(decimal: String): String {
        return BigInteger(decimal).toString(2)
    }
    private fun decToOct(decimal: String): String {
        val dec = BigInteger(decimal)
        var oct = ""

        if (dec == BigInteger.ZERO) {
            oct = "0"
        } else {
            var num = dec
            while (num > BigInteger.ZERO) {
                val rem = num.mod(BigInteger.valueOf(8))
                oct = rem.toString() + oct
                num = num.divide(BigInteger.valueOf(8))
            }
        }

        return StringBuilder(oct).toString()
    }

    private fun decToHex(decimal: String): String {
        val dec = BigInteger(decimal)
        return StringBuilder(dec.toString(16).uppercase()).toString()
    }

    private fun hexToBin(hex: String): String {
        val decimal = hexToDec(hex)
        return decToBin(decimal)
    }

    private fun hexToOct(hex: String): String {
        val dec = hexToDec(hex)
        return decToOct(dec)
    }

    private fun hexToDec(input: String): String {
        var sum = BigInteger.ZERO
        val hexDigits = "0123456789ABCDEF"
        for (i in input.indices) {
            val digit = hexDigits.indexOf(input[i])
            sum = sum.multiply(BigInteger.valueOf(16)).add(BigInteger.valueOf(digit.toLong()))
        }
        return StringBuilder(sum.toString()).toString()
    }
}