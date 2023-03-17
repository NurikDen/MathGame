package com.example.mathgame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mathtext: TextView? = null
    private var answertextview: TextView? = null
    private var answerbutton: Button? = null
    private var nextbutton: Button? = null
    private var edittextnumber: EditText? = null
    private var comands: Array<String> = arrayOf()
    private var random: Random? = null
    private var f = 0
    private var s = 0
    private var r = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mathtext = findViewById(R.id.primer)
        answertextview = findViewById(R.id.yes)
        answerbutton = findViewById(R.id.answer)
        nextbutton = findViewById(R.id.next)
        edittextnumber = findViewById(R.id.editTextNumber)
        comands = arrayOf("+", "*")
        random = Random()
        generate()
        answerbutton!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
             onAnswer()
            }
        })
        nextbutton!!.setOnClickListener(View.OnClickListener { next() })
    }

    private fun generate() {
        f = random!!.nextInt(99) + 1
        s = random!!.nextInt(99) + 1
        val command = comands[random!!.nextInt(comands.size)]
        when (command) {
            "+" -> r = f + s
            "*" -> r = f * s
        }
        mathtext!!.text = "$f $command $s =?"
    }

    private fun onAnswer() {
        var v = false
        try {
            val res = edittextnumber!!.text.toString().toInt()
            v = r == res
        } catch (e: Exception) {
            answertextview!!.visibility = View.VISIBLE
            answertextview!!.text = "It's not number"
        }
        answerbutton!!.visibility = View.GONE
        answertextview!!.setBackgroundColor(
            if (v) resources.getColor(R.color.green) else resources.getColor(
                R.color.red
            )
        )
        answertextview!!.setText(if (v) R.string.right_answer else R.string.wrong_answer)
        answertextview!!.visibility = View.VISIBLE
        nextbutton!!.visibility = View.VISIBLE
    }

    private operator fun next() {
        nextbutton!!.visibility = View.GONE
        answertextview!!.visibility = View.GONE
        answerbutton!!.visibility = View.VISIBLE
        generate()
    }
}