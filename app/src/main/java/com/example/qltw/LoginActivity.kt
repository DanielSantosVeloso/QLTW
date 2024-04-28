package com.example.qltw

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var sqlHelper: sqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        sqlHelper = sqlHelper(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val registrarTexto: TextView = findViewById(R.id.msgcad)
        registrarTexto.setOnClickListener{
            val  intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
            finish()
    }
        val Logar: Button = findViewById(R.id.Logar)
        Logar.setOnClickListener{
            loginBase()
}


}

    private fun loginBase() {
        val EmailLogin:EditText = findViewById(R.id.editEmail)
        val senhaLogin:EditText = findViewById(R.id.editSenha)
        val email = EmailLogin.text.toString()
        val password = senhaLogin.text.toString()

        val usuarioExiste = sqlHelper.lerUsuario(password,email)
        if(usuarioExiste){
            Toast.makeText(this, "Login feito com sucesso",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else{
            Toast.makeText(this,"Login falho",Toast.LENGTH_SHORT).show()

        }
    }}
