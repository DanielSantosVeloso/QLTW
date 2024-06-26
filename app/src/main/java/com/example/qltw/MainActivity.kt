package com.example.qltw

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var buttonProcura: Button
    private lateinit var userConnected: TextView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    var contProc2 = 0
    var contador=0
    var saidaCheckV = ""
    var chegadaCheckV = ""
    var valorCheckV = ""
    var contadorCheckV = ""
    private lateinit var textSaidaExibir:TextView
    private lateinit var textChegadaExibir:TextView
    private lateinit var textNomeExibir:TextView
    private val ticket = listOf(
        ticket("SP","RJ","1500"),
        ticket("FAR","MG","2500"),
        ticket("GO","MA","4000"),
        ticket("AL","AP","3400"),
        ticket("AM","CE","5000"),
        ticket("DF","PR","6000"),
        ticket("PE","PA","7500"),
        ticket("SC","TO","2000"),
        ticket("SE","RO","3000"),
        ticket("RR","RN","7000"),
        ticket("RJ","SP","1500"),
        ticket("MG","FAR","2500"),
        ticket("MA","GO","4000"),
        ticket("AP","AL","3400"),
        ticket("CE","AM","5000"),
        ticket("PR","DF","6000"),
        ticket("PA","PE","7500"),
        ticket("TO","SC","2000"),
        ticket("RO","SE","3000"),
        ticket("RN","RR","7000"),
    )

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
        buttonProcura = findViewById(R.id.buttonProcurar)
        buttonNext = findViewById(R.id.Button_next)
        buttonBefore = findViewById(R.id.Button_before)
        userConnected = findViewById(R.id.userConnected)

        //Recebendo os dados do Cookie
        sharedPreferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)

        userConnected.text = username

        //iniciando editor
        val editor = sharedPreferences.edit()

        buttonNext.setOnClickListener{
           next()
            changeTicket()
        }
        buttonBefore.setOnClickListener{
           before()
            changeTicket()
        }

        buttonProblemas.setOnClickListener{
            verProblemas(saidaCheckV, chegadaCheckV, valorCheckV, contador)
        }
        buttonProcura.setOnClickListener {
            ProcuraTicket()
        }

        //botão de menu
        val buttonMenu : ImageButton = findViewById(R.id.buttonMenu)
        val navView: NavigationView = findViewById(R.id.nav_view)

        //menu
        navView.visibility = View.GONE
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sair -> {
                    Toast.makeText(this, "Deslogando...", Toast.LENGTH_SHORT).show()
                    //limpa cookies
                    editor.clear()
                    editor.apply()
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
        if(contador>=19) {
        }
        else{
            contador++
        }
    }

    //Botao before, volta para o ticket anterior
    private fun before(){
        if(contador<=0){
        }
        else{
            contador--
        }

    }

    //Precisa de comentario? Só um botao que volta pro Login
    fun voltar(view: View){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        finish()
    }

    private fun ProcuraTicket(){

        var achou = 0
        val Saidatextinho = textSaida.text.toString()
        val Chegadatextinho = textChegada.text.toString()

        for(contProc in 0..19 ){

            val contAtual = ticket[contProc2]

            if(Saidatextinho == contAtual.saida && Chegadatextinho == contAtual.chegada) {
                contador = contProc2
                changeTicket()
                achou = 1

            }
            else{
                contProc2++

            }
        }
        if(achou == 0){

            Toast.makeText(this,"Ticket não disponivel",Toast.LENGTH_SHORT).show()
        }
        contProc2 = 0
        achou = 0



    }
    private fun changeTicket(){
        //escolhendo texto do ticket
        val textAtual = ticket[contador]
        contadorCheckV = contador.toString()
        when(contador) {
            0 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }

            1 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textChegadaExibir.text = textAtual.chegada.toString()
                textSaidaExibir.text = textAtual.saida.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }

            2 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            3 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            4 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            5 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            6 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            7 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            8 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            9 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            10 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            11 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            12 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            13 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            14 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            15 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            16 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            17 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            18 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }
            19 -> {
                saidaCheckV = textAtual.saida
                chegadaCheckV = textAtual.chegada
                valorCheckV = textAtual.valor
                textSaidaExibir.text = textAtual.saida.toString()
                textChegadaExibir.text = textAtual.chegada.toString()
                textNomeExibir.text = textAtual.valor.toString()
            }

        }
    }

    private fun verProblemas(saidaCheck: String, chegadaCheck: String, valorCheck: String, contadorCheck: Int){
        val intent = Intent(applicationContext, ProblemasActivity::class.java)
        intent.putExtra("saida", saidaCheck)
        intent.putExtra("chegada", chegadaCheck)
        intent.putExtra("valor", valorCheck)
        intent.putExtra("contador", contadorCheck)
        startActivity(intent)
        finish()
    }
}