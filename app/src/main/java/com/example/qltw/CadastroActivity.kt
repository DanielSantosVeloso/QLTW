package com.example.qltw


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        // Initialize Firebase Auth
        auth = Firebase.auth

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val registrarTextos: TextView = findViewById(R.id.text_aviso_login)
        registrarTextos.setOnClickListener{
            val  intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


                }
    fun fazerCadastro(View : View) {
        val email = findViewById<EditText>(R.id.Email_cad)
        val senha = findViewById<EditText>(R.id.Senha_cad)

        if(email.text.isEmpty() || senha.text.isEmpty()){
            Toast.makeText(this, "Por favor preencha os campos", Toast.LENGTH_SHORT)
                .show()
            return

        }
        val  inputEmail = email.toString()
        val inputPassword = senha.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext,
                        "Registro complesto",
                        Toast.LENGTH_SHORT,
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Um erro ocorreu ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()

        }}}





