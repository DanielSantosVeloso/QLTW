package com.example.qltw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var message: EditText;
    private lateinit var textSaida : EditText
    private lateinit var textChegada : EditText
    private lateinit var buttonNext : ImageButton
    private lateinit var buttonBefore : ImageButton
    private lateinit var buttonProblemas : Button
    lateinit var toggle: ActionBarDrawerToggle
    var contador=0
    var saidaCheckV = ""
    var chegadaCheckV = ""
    var contadorCheckV = ""
    private lateinit var textSaidaExibir:TextView
    private lateinit var textChegadaExibir:TextView
    private lateinit var textNomeExibir:TextView
    private val ticket =(listOf(
    ticket("SP","RJ","1500"),
        ticket("FAR","MG","2500"),
        ticket("GO","MA","4000"),
    ))

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textSaida = findViewById(R.id.textSaida)
        textChegada = findViewById(R.id.textChegada)
        textSaidaExibir =findViewById(R.id.textSaidaExibir)
        textChegadaExibir = findViewById(R.id.textChegadaExibir)
        textNomeExibir = findViewById(R.id.textNomeExibir)
        buttonProblemas = findViewById(R.id.buttonProblemas)

        buttonNext = findViewById(R.id.Button_next)
        buttonBefore = findViewById(R.id.Button_before)
        buttonNext.setOnClickListener{
           next()
            changeTicket()
        }
        buttonBefore.setOnClickListener{
           before()
            changeTicket()
        }

        buttonProblemas.setOnClickListener{
            verProblemas(saidaCheckV, chegadaCheckV, contadorCheckV)
        }

        //botão de menu
        val buttonMenu : ImageButton = findViewById(R.id.buttonMenu)
        val navView: NavigationView = findViewById(R.id.nav_view)

        //menu
        navView.visibility = View.GONE
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_carrinho -> Toast.makeText(this, "Carrinho", Toast.LENGTH_SHORT).show()
                R.id.nav_inicial -> Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                R.id.nav_sair -> {
                    Toast.makeText(this, "Voltando para a tela de Login", Toast.LENGTH_SHORT).show()
                    val  intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        //botao do menu
        buttonMenu.setOnClickListener(){
            if(navView.isVisible){
                navView.visibility = View.GONE
            }else{
                navView.visibility = View.VISIBLE
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        changeTicket()
    }

    //recebendo os dados escritos pelo usuario
    fun buttonProcurar(view: View) {
        val validSaida = textSaida.text.toString()
        val validChegada = textChegada.text.toString()
        val validando = validacao(validSaida, validChegada)
        if(validando){
            //enviarParaProblemas(validSaida, validChegada)
        }else{
            Toast.makeText(this, "Preencha os campos de Saída e Chegada", Toast.LENGTH_SHORT).show()
        }
    }
    private fun validacao(textoSaida: String, textoChegada: String):Boolean {
        var validado = true
        if (textoSaida.isBlank() && textoChegada.isBlank()) {
            validado = false
        }
        return validado
    }

    /* Nao é mais utilizado
    //enviando para a tela de compra
    private fun enviarParaProblemas(saidaCheck: String, chegadaCheck: String){
        val intent = Intent(applicationContext, ProblemasActivity::class.java)
        intent.putExtra("saida", saidaCheck)
        intent.putExtra("chegada", chegadaCheck)
        startActivity(intent)
        finish()
    }
     */

    //Botao next, passa para o proximo ticket
    private fun next(){
        if(contador>=2) {
        Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
        }
        else{
            contador++
            Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
        }
    }

    //Botao before, volta para o ticket anterior
    private fun before(){
        if(contador<=0){
            Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
        }
        else{
            contador--
            Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
        }

    }

    //Precisa de comentario? Só um botao que volta pro Login
    fun voltar(view: View){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        finish()
    }
    private fun changeTicket(){
        //escolhendo texto do ticket
        val textAtual = ticket[contador]
        when(contador) {
            0 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                contadorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }

            1 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                contadorCheckV = textAtual.valor
                textChegadaExibir.text = textAtual.chegada.toString()
                textSaidaExibir.text = textAtual.saida.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }

            2 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                contadorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
        }
    }

    private fun verProblemas(saidaCheck: String, chegadaCheck: String, contadorCheck: String){
        val intent = Intent(applicationContext, ProblemasActivity::class.java)
        intent.putExtra("saida", saidaCheck)
        intent.putExtra("chegada", chegadaCheck)
        intent.putExtra("contador", contadorCheck)
        startActivity(intent)
        finish()
    }
}