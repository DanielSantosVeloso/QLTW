package com.example.qltw


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import android.content.Intent

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class CadastroActivity : AppCompatActivity() {


    private lateinit var sqlHelper: sqlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        sqlHelper = sqlHelper(this)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val registrarTextos: TextView = findViewById(R.id.text_aviso_login)
        registrarTextos.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)



        }
        val cadastrar:Button = findViewById(R.id.cadastrar)
        cadastrar.setOnClickListener{
            cadastroBasedeDados()

        }

    }

    private fun cadastroBasedeDados() {
        val NomeUsuario: EditText = findViewById(R.id.Nomecad)
        val passwordUsuario: EditText = findViewById(R.id.Senha_cad)
        val emailUsuario: EditText = findViewById(R.id.Email_cad)
        val username = NomeUsuario.text.toString()
        val password = passwordUsuario.text.toString()
        val email = emailUsuario.text.toString()

        val usuarioExiste = sqlHelper.existeUsuario(email)

        val validando = validacao(email, password, username)

        if(validando){
            if(usuarioExiste){
                Toast.makeText(this, "Email ja cadastrado", Toast.LENGTH_SHORT).show()
            }else{
                val colunaPeloID = sqlHelper.inserirUsuario(username, password, email)
                if (colunaPeloID != -1L) {
                    Toast.makeText(this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro no cadastro", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "Preencha os campos para realizar o Cadastro", Toast.LENGTH_SHORT).show()
        }

    }
    private fun validacao(emailUsuario: String, senhaUsuario: String, nomeUsuario: String):Boolean {
        var validado = true
        if (emailUsuario.isBlank() || senhaUsuario.isBlank() || nomeUsuario.isBlank()) {
            validado = false
        }
        return validado
    }
}


