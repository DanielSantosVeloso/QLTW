package com.example.qltw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
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
    lateinit var toggle: ActionBarDrawerToggle
    var contador=0
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

        buttonNext = findViewById(R.id.Button_next)
        buttonBefore = findViewById(R.id.Button_before)
        buttonNext.setOnClickListener{
           next()
            ChangeTicket()
        }
        buttonBefore.setOnClickListener{
           before()
            ChangeTicket()
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
        ChangeTicket()
    }

    //recebendo os dados escritos pelo usuario
    fun buttonProcurar(view: View) {
        val validSaida = textSaida.text.toString()
        val validChegada = textChegada.text.toString()
        val validando = validacao(validSaida, validChegada)
        if(validando){
            enviarParaCompra(validSaida, validChegada)
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

    //enviando para a tela de compra
    private fun enviarParaCompra(saidaCheck: String, chegadaCheck: String){
        val intent = Intent(applicationContext, CompraActivity::class.java)
        intent.putExtra("saida", saidaCheck)
        intent.putExtra("chegada", chegadaCheck)
        startActivity(intent)
        finish()
    }
 private fun next(){
     if(contador>=2) {

         Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
     }
     else{

         contador++
         Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
     }
 }
    private fun before(){
        if(contador<=0){

            Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
        }
        else{

            contador--
            Toast.makeText(this, "$contador",Toast.LENGTH_SHORT).show()
        }

    }
    fun voltar(view: View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        finish()
    }
    private fun ChangeTicket(){
        //escolhendo texto do ticket
        val textAtual = ticket[contador]


        when(contador){

            0->{
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()

            }
            1->{
                textChegadaExibir.text = textAtual.chegada.toString()
                textSaidaExibir.text = textAtual.saida.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            2->{
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }

        }

    }
}