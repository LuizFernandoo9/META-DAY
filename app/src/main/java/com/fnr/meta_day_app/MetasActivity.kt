package com.fnr.meta_day_app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.fnr.meta_day_app.R.id.toolbar4

class MetasActivity : AppCompatActivity() {

    private lateinit var toolbar4: Toolbar
    private lateinit var toolbar5: Toolbar
    private lateinit var toolbar6: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metas)

        toolbar4 = findViewById(R.id.toolbar4)
        toolbar5 = findViewById(R.id.toolbar5)
        toolbar6 = findViewById(R.id.toolbar6)

        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)

        button5.setOnClickListener {
            concluirMetaCaminhada(toolbar4)
        }
        button6.setOnClickListener {
            concluirMetaBeberagua(toolbar5)
        }
        button7.setOnClickListener {
            concluirMetaCorrida(toolbar6)
        }
    }

    private fun concluirMetaCaminhada(toolbar: Toolbar) {
        toolbar.setBackgroundColor(Color.GREEN)
        toolbar.postDelayed({
            toolbar.visibility = View.GONE
        }, 500)
    }

    private fun concluirMetaBeberagua(toolbar: Toolbar) {
        toolbar.setBackgroundColor(Color.GREEN)
        toolbar.postDelayed({
            toolbar.visibility = View.GONE
        }, 500)
    }

    private fun concluirMetaCorrida(toolbar: Toolbar) {
        toolbar.setBackgroundColor(Color.GREEN)
        toolbar.postDelayed({
            toolbar.visibility = View.GONE
        }, 500)
    }
}
