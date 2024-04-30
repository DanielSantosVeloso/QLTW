package com.example.qltw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
    private lateinit var problema1: TextView
    private lateinit var problemaB1: Button
    private lateinit var problemaB2: Button
    private lateinit var problema2: TextView
    private lateinit var problema3: TextView
    private lateinit var problema4: TextView
    private lateinit var problema5: TextView
    private lateinit var descricao: TextView
    private lateinit var buttonVoltar: Button
    private var contador = 0
    private var problemaSelecionado = 0

    private val problemas = listOf(
        problemas("Texto1", "Texto2", "Texto3", "Texto4", "Texto5"),
        problemas("a", "a", "a", "a", "a"),
        problemas("b", "b", "b", "b", "b"),
    )

    private val descricoes = listOf(
        descricaoProblemas("Descricao1", "Descricao2", "Descricao3", "Descricao4", "Descricao5"),
        descricaoProblemas("c", "dedede", "c", "c", "c"),
        descricaoProblemas("d", "d", "d", "d", "d"),
    )

    @SuppressLint("MissingInflatedId")
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
        problema1 = findViewById(R.id.problema1)
        problema2 = findViewById(R.id.problema2)
        problema3 = findViewById(R.id.problema3)
        problema4 = findViewById(R.id.problema4)
        problema5 = findViewById(R.id.problema5)
        descricao = findViewById(R.id.descricao)
        problemaB1 = findViewById(R.id.problemaB1)
        problemaB2 = findViewById(R.id.problemaB2)
        buttonVoltar = findViewById(R.id.buttonVoltar)

        //recebendo os dados de MainActivity
        val dados: Bundle? = intent.extras
        val saida = dados?.getString("saida")
        val chegada = dados?.getString("chegada")
        val valor = dados?.getString("valor")
        contador = dados?.getInt("contador")!!

        saidaV.text = saida
        chegadaV.text = chegada
        valorV.text = valor
        changePerguntas()

        val descAtual = descricoes[problemaSelecionado]

        problemaB1.setOnClickListener { descricao.text = descAtual.descricao1}
        problemaB2.setOnClickListener { descricao.text = descAtual.descricao2 }

        buttonVoltar.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun changePerguntas() {
        val probAtual = problemas[contador]
        when (contador) {
            0 -> {
                problema1.text = probAtual.texto1
                problema2.text = probAtual.texto2
                problema3.text = probAtual.texto3
                problema4.text = probAtual.texto4
                problema5.text = probAtual.texto5
            }

            1 -> {
                problema1.text = probAtual.texto1
                problema2.text = probAtual.texto2
                problema3.text = probAtual.texto3
                problema4.text = probAtual.texto4
                problema5.text = probAtual.texto5
            }

            2 -> {
                problema1.text = probAtual.texto1
                problema2.text = probAtual.texto2
                problema3.text = probAtual.texto3
                problema4.text = probAtual.texto4
                problema5.text = probAtual.texto5
            }
        }
    }

    private fun changeDescricao() {
        val descAtual = descricoes[contador]
        when (contador) {
            0 -> {
                descricao.text = descAtual.descricao1
            }
            1 -> {
                descricao.text = descAtual.descricao1
            }

            2 -> {
                descricao.text = descAtual.descricao3
            }
        }
    }
}