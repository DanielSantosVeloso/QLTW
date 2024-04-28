package com.example.qltw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var message: EditText;
    private lateinit var textSaida : EditText
    private lateinit var textChegada : EditText
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textSaida = findViewById(R.id.textSaida)
        textChegada = findViewById(R.id.textChegada)

        val buttonMenu : ImageButton = findViewById(R.id.buttonMenu)
        val navView: NavigationView = findViewById(R.id.nav_view)

        //menu
        navView.visibility = View.GONE
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sair -> Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
                R.id.nav_carrinho -> Toast.makeText(this, "Carrinho", Toast.LENGTH_SHORT).show()
                R.id.nav_inicial -> Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
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
    }

    //recebendo os dados escritos pelo usuario
    fun buttonProcurar(view: View) {
        val validSaida = textSaida.text.toString()
        val validChegada = textSaida.text.toString()
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
    }
}