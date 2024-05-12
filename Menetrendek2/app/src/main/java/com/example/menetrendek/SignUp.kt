package com.example.menetrendek

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
class SignUp : AppCompatActivity() {
    val TAG = SignUp::class.qualifiedName
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtPasswordAgain: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        edtPasswordAgain = findViewById(R.id.edt_password_again)
        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_goto_signup)

        mAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val passwordAgain = edtPasswordAgain.text.toString()
            if (validatePasswords(password, passwordAgain)){
                signUp(email, password)
            }
        }
    }
    private fun signUp(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@SignUp, MainPageActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.i(TAG, "authenFail", task.exception)
                    Toast.makeText(
                        this@SignUp,
                        task.exception?.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun validatePasswords(password: String, passwordAgain: String): Boolean{
        if (password != passwordAgain) {
            Toast.makeText(
                this@SignUp,
                "Passwords don't match.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

}