package com.example.qltw

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}

        editTextEmail = findViewById(R.id.editEmail)
        editTextPassword = findViewById(R.id.editSenha)
        buttonLogin = findViewById(R.id.Logar)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid
                    if (userId != null) {
                        db.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                if (document != null && document.exists()) {
                                    val name = document.getString("name")

                                    Toast.makeText(this, "Bem-vindo, $name", Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this, MainActivity::class.java).apply {
                                        putExtra("name", name)
                                    }
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "Erro ao recuperar informações do usuário", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Erro ao acessar o Firestore", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Erro ao obter ID do usuário", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Erro ao fazer login: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }}



