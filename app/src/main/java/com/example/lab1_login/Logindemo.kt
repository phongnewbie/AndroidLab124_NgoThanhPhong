package com.example.lab1_login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class Logindemo:ComponentActivity() {
    private lateinit var id: EditText
    private lateinit var pass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        this.id = findViewById<EditText>(R.id.username)
        this.pass = findViewById<EditText>(R.id.password)
        this.btnLogin = findViewById<Button>(R.id.login)
        this.btnRegister = findViewById<Button>(R.id.register)

        btnLogin.setOnClickListener{
            login()
        }

        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun login() {
        var email: String
        var pass: String

        email = this.id.text.toString()
        pass = this.pass.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email!!", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập password!!", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register() {
        var email: String
        var pass: String

        email = this.id.text.toString()
        pass = this.pass.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email!!", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập password!!", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
