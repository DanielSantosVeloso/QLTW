package com.example.qltw

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProblemasActivity : AppCompatActivity() {
    private lateinit var saidaV: TextView
    private lateinit var chegadaV: TextView
    private lateinit var valorV: TextView
    private lateinit var sqlHelper: sqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_problemas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        saidaV = findViewById(R.id.textSaidaProblema)
        chegadaV = findViewById(R.id.textoChegadaProblema)
        valorV = findViewById(R.id.textValorProblema)

        //recebendo os dados de MainActivity
        val dados: Bundle? = intent.extras
        val saida = dados?.getString("saida")
        val chegada = dados?.getString("chegada")
        val contador = dados?.getString("contador")

        saidaV.text = saida
        chegadaV.text = chegada
        valorV.text = contador

    }
}