package com.example.qltw


import android.app.Activity
import android.content.ContentValues.TAG
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CadastroActivity : AppCompatActivity() {

    private lateinit var NomeUsuario: EditText
    private lateinit var passwordUsuario: EditText
    private lateinit var emailUsuario: EditText

    private lateinit var auth: FirebaseAuth
    private var fbdb = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

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
        val cadastrar: Button = findViewById(R.id.cadastrar)
        cadastrar.setOnClickListener {

            NomeUsuario = findViewById(R.id.Nomecad)
            passwordUsuario = findViewById(R.id.Senha_cad)
            emailUsuario = findViewById(R.id.Email_cad)

            val username = NomeUsuario.text.toString()
            val password = passwordUsuario.text.toString()
            val email = emailUsuario.text.toString()

            val userMap = hashMapOf(
                "nome" to username,
                "senha" to password,
                "email" to email,
            )

            fbdb.collection("users")
                .add(userMap)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "Documento blablabla Certo - ID: ${documentReference.id}")
                    Toast.makeText(this, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Errou feiao ala", e)
                    Toast.makeText(this, "Falha ao Cadastrar", Toast.LENGTH_SHORT).show()
                }
        }
    }
    private fun validacao(
        emailUsuario: String,
        senhaUsuario: String,
        nomeUsuario: String
    ): Boolean {
        var validado = true
        if (emailUsuario.isBlank() || senhaUsuario.isBlank() || nomeUsuario.isBlank()) {
            validado = false
        }
        return validado
    }
}

