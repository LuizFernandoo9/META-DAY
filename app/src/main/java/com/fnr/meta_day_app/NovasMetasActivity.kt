package com.fnr.meta_day_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class NovasMetasActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_novas_metas)

        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)

        val goals = arrayOf("Corrida", "Beber água", "Caminhada")

        val onClickListener = View.OnClickListener {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Escolha uma meta")
        builder.setItems(goals) { _, which ->
        val selectedGoal = goals[which]
        Toast.makeText(this, "Meta selecionada: $selectedGoal", Toast.LENGTH_SHORT).show()
        }

        val dialog = builder.create()
        dialog.show()
        }

        button2.setOnClickListener(onClickListener)
        button3.setOnClickListener(onClickListener)
        button4.setOnClickListener(onClickListener)
        }
        }