package com.example.qltw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView

class ProblemasActivity : AppCompatActivity() {
    private lateinit var saidaV: TextView
    private lateinit var chegadaV: TextView
    private lateinit var valorV: TextView

    private lateinit var problemaB1: Button
    private lateinit var problemaB2: Button
    private lateinit var problemaB3: Button
    private lateinit var problemaB4: Button
    private lateinit var problemaB5: Button

    private lateinit var descricao: TextView
    private lateinit var buttonVoltar: ImageButton

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
        problemaB1 = findViewById(R.id.problemaB1)
        problemaB2 = findViewById(R.id.problemaB2)
        problemaB3 = findViewById(R.id.problemaB3)
        problemaB4 = findViewById(R.id.problemaB4)
        problemaB5 = findViewById(R.id.problemaB5)
        descricao = findViewById(R.id.descricao)
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

        problemaB1.setOnClickListener { problemaSelecionado = 0; changeDescricao() }
        problemaB2.setOnClickListener { problemaSelecionado = 1; changeDescricao() }
        problemaB3.setOnClickListener { problemaSelecionado = 2; changeDescricao() }
        problemaB4.setOnClickListener { problemaSelecionado = 3; changeDescricao() }
        problemaB5.setOnClickListener { problemaSelecionado = 4; changeDescricao() }

        buttonVoltar.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun changePerguntas() {
        val probAtual = problemas[contador]
        when(contador) {
            0 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            1 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            2 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }
        }
    }

    private fun changeDescricao() {
        val descAtual = descricoes[contador]
        when (contador) {
            0 -> {
                when(problemaSelecionado) {
                    0 -> { descricao.text = descAtual.descricao1 }
                    1 -> { descricao.text = descAtual.descricao2 }
                    2 -> { descricao.text = descAtual.descricao3 }
                    3 -> { descricao.text = descAtual.descricao4 }
                    4 -> { descricao.text = descAtual.descricao5 }
                }
            }
            1 -> {
                when(problemaSelecionado) {
                    0 -> { descricao.text = descAtual.descricao1 }
                    1 -> { descricao.text = descAtual.descricao2 }
                    2 -> { descricao.text = descAtual.descricao3 }
                    3 -> { descricao.text = descAtual.descricao4 }
                    4 -> { descricao.text = descAtual.descricao5 }
                }
            }
            2 -> {
                when(problemaSelecionado) {
                    0 -> { descricao.text = descAtual.descricao1 }
                    1 -> { descricao.text = descAtual.descricao2 }
                    2 -> { descricao.text = descAtual.descricao3 }
                    3 -> { descricao.text = descAtual.descricao4 }
                    4 -> { descricao.text = descAtual.descricao5 }
                }
            }
        }
    }
}