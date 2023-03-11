package com.example.programmercalc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.util.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private val binaryRegex = "[01]+".toRegex()
    private val octalRegex = "[0-7]+".toRegex()
    private val decimalRegex = "\\d+".toRegex()
    private val hexRegex = "[\\da-fA-F]+".toRegex()

    private val conversionTypes = arrayOf("Binary", "Octal", "Decimal", "Hexa")


    private lateinit var conversionTypeSpinner: Spinner
    private lateinit var binRes: TextView
    private lateinit var octRes: TextView
    private lateinit var decRes: TextView
    private lateinit var hexRes: TextView
    private lateinit var resultTextView: TextView
    private lateinit var equalBtn: Button
    private lateinit var clearBtn: Button
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        equalBtn = findViewById(R.id.equal_btn)
        //setupEqualBtn()
        setupConversionTypeListener()

        setupViews()

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
        resultTextView = findViewById(R.id.text_to_append_number)
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

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conversionTypeSpinner.adapter = adapter
    }

    private fun setupNumberButtons() {
        val numberButtons = listOf(
            zeroBtn,
            oneBtn,
            twoBtn,
            threeBtn,
            fourBtn,
            fiveBtn,
            sixBtn,
            sevenBtn,
            eightBtn,
            nineBtn,
            aBtn,
            bBtn,
            cBtn,
            dBtn,
            eBtn,
            fBtn
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
            resultTextView.text = ""
        }
    }

    private fun setupRemoveButton() {
        rmBtn.setOnClickListener {
            when {
                /*binRes.text.isNotEmpty() -> binRes.text = binRes.text.dropLast(1)
                octRes.text.isNotEmpty() -> octRes.text = octRes.text.dropLast(1)
                decRes.text.isNotEmpty() -> decRes.text = decRes.text.dropLast(1)
                hexRes.text.isNotEmpty() -> hexRes.text = hexRes.text.dropLast(1)*/
                resultTextView.text.isNotEmpty() -> resultTextView.text =
                    resultTextView.text.dropLast(1)
            }
        }
    }
    /* private fun setupEqualBtn() {
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
     }*/

    private fun setupConversionTypeListener() {

            val initialPosition = conversionTypeSpinner.selectedItemPosition
            conversionTypeSpinner.setSelection(initialPosition, false)
            conversionTypeSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val input = getInputText()
                        hideButtonsForConversionType(conversionTypes[position])


                        when (conversionTypes[position]) {
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
            }


        }
    }

    private fun getInputText() = resultTextView.text.toString()

    private fun appendText(text: String) {
        val currentInput = resultTextView.text.toString()
        resultTextView.text = currentInput + text
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
        var decimal = 0
        var power = 0
        for (i in binary.length - 1 downTo 0) {
            val digit = binary[i].toString().toInt()
            decimal += digit * 2.0.pow(power.toDouble()).toInt()
            power++
        }
        return decimal.toString()
    }

    private fun binToHex(binary: String): String {
        val decimal = Integer.parseInt(binary, 2)
        return Integer.toHexString(decimal).uppercase()
    }

    private fun octToBin(octal: String): String {
        val decimal = octToDec(octal)
        return decToBin(decimal)
    }

    private fun octToDec(octal: String): String {
        return try {
            Integer.parseInt(octal, 8).toString()
        } catch (e: Exception) {
            ""
        }
    }

    private fun octToHex(oct: String): String {
        val decimal = octToDec(oct)
        return decToHex(decimal)
    }

    private fun decToBin(decimal: String): String {
        return Integer.toBinaryString(decimal.toInt())
    }

    private fun decToOct(decimal: String): String {
        val dec = decimal.toInt()
        var oct = ""

        if (dec == 0) {
            oct = "0"
        } else {
            var num = dec
            while (num > 0) {
                oct = (num % 8).toString() + oct
                num /= 8
            }
        }

        return oct
    }

    private fun decToHex(decimal: String): String {
        val dec = decimal.toLong()
        return java.lang.Long.toHexString(dec).uppercase()
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
        var sum = 0
        val hexDigits = "0123456789ABCDEF"
        for (i in input.indices) {
            val digit = hexDigits.indexOf(input[i])
            sum = sum * 16 + digit
        }
        return sum.toString()
    }
}