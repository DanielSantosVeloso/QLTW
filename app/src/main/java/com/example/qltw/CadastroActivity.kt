package com.example.qltw



import android.os.Bundle
import android.util.Log
import android.widget.Toast

import android.content.Intent
import android.text.TextUtils


import android.widget.Button
import android.widget.EditText


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

import com.google.firebase.firestore.FirebaseFirestore



class CadastroActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextName: EditText
    private lateinit var buttonRegister: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var mensagem = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}

        editTextEmail = findViewById(R.id.Email_cad)
        editTextPassword = findViewById(R.id.Senha_cad)
        editTextName = findViewById(R.id.Nomecad)
        buttonRegister = findViewById(R.id.cadastrar)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        buttonRegister.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val name = editTextName.text.toString().trim()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) ) {
                Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(name.length > 40){
                Toast.makeText(this, "O nome não pode ter mais de 40 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registerUser(email, password, name)
        }
    }

    private fun registerUser(email: String, password: String, name: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid
                    val user = hashMapOf(
                        "name" to name,
                        "email" to email
                    )

                    userId?.let {
                        db.collection("users").document(it).set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this,LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Log.w("FirestoreDebug", "Erro ao salvar dados", e)
                                Toast.makeText(this, "Erro ao salvar dados", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    val errorMessage: String = when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> "A senha deve conter no mínimo 6 caracteres."
                        is FirebaseAuthInvalidCredentialsException -> "Insira um email válido."
                        is FirebaseAuthUserCollisionException -> "Email já cadastrado."
                        else -> "Desconhecido. Por favor, tente novamente mais tarde."
                    }
                    Toast.makeText(this, "Erro ao registrar usuário: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

