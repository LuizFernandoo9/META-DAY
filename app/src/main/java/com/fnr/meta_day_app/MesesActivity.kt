package com.fnr.meta_day_app;

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meses.R

    class MesesActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.`meses.xml`)

            val button1 = findViewById<Button>(R.id.button1)
            val button2 = findViewById<Button>(R.id.button2)
            val button3 = findViewById<Button>(R.id.button3)
            val button4 = findViewById<Button>(R.id.button4)
            val button5 = findViewById<Button>(R.id.button5)
            val button6 = findViewById<Button>(R.id.button6)
            val button7 = findViewById<Button>(R.id.button7)
            val button8 = findViewById<Button>(R.id.button8)
            val button9 = findViewById<Button>(R.id.button9)
            val button10 = findViewById<Button>(R.id.button10)
            val button11 = findViewById<Button>(R.id.button11)
            val button12 = findViewById<Button>(R.id.button12)

            button1.setOnClickListener {
                Toast.makeText(this, "Janeiro clicked", Toast.LENGTH_SHORT).show()
            }

            button2.setOnClickListener {
                Toast.makeText(this, "Fevereiro clicked", Toast.LENGTH_SHORT).show()
            }

            button3.setOnClickListener {
                Toast.makeText(this, "Março clicked", Toast.LENGTH_SHORT).show()
            }

            button4.setOnClickListener {
                Toast.makeText(this, "Abril clicked", Toast.LENGTH_SHORT).show()
            }

            button5.setOnClickListener {
                Toast.makeText(this, "Maio clicked", Toast.LENGTH_SHORT).show()
            }

            button6.setOnClickListener {
                Toast.makeText(this, "Junho clicked", Toast.LENGTH_SHORT).show()
            }

            button7.setOnClickListener {
                Toast.makeText(this, "Julho clicked", Toast.LENGTH_SHORT).show()
            }

            button8.setOnClickListener {
                Toast.makeText(this, "Agosto clicked", Toast.LENGTH_SHORT).show()
            }

            button9.setOnClickListener {
                Toast.makeText(this, "Setembro clicked", Toast.LENGTH_SHORT).show()
            }

            button10.setOnClickListener {
                Toast.makeText(this, "Outubro clicked", Toast.LENGTH_SHORT).show()
            }

            button11.setOnClickListener {
                Toast.makeText(this, "Novembro clicked", Toast.LENGTH_SHORT).show()
            }

            button12.setOnClickListener {
                Toast.makeText(this, "Dezembro clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

}




