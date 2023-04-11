package umn.ac.id.codek.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import umn.ac.id.codek.R
import umn.ac.id.codek.profile.Profile

class Login : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //HIDE ACTION BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        val currentUser = auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this, Home::class.java))
            finish()
        }
        login()
    }

    override fun onBackPressed() {

    }

    private fun login(){
        loginBtn.setOnClickListener {
            if (TextUtils.isEmpty(usernameInput.text.toString())){
                usernameInput.error = "Please enter email"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passwordInput.text.toString())){
                passwordInput.error = "Please enter password"
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(usernameInput.text.toString(), passwordInput.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this, Home::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Login failed, please try again! ", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        registerText.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
}