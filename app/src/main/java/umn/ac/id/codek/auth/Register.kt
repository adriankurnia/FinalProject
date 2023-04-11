package umn.ac.id.codek.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.passwordInput
import umn.ac.id.codek.R
import umn.ac.id.codek.account

class Register : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    //var database: FirebaseDatabase? = null
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //HIDE ACTION BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        // get firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        register()
    }

    private fun register(){
        registerBtn.setOnClickListener {

            if (TextUtils.isEmpty(firstNameInput.text.toString())){
                firstNameInput.error = "Please enter first name"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(lastNameInput.text.toString())){
                lastNameInput.error = "Please enter last name"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(emailInput.text.toString())){
                emailInput.error = "Please enter email"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passwordInput.text.toString())){
                passwordInput.error = "Please enter password"
                return@setOnClickListener
            }

            var avatarList: ArrayList<Int> = ArrayList()
            avatarList.add(1)
            Log.e("avatar list", avatarList.toString())

            val account = account(
                firstNameInput.text.toString(),
                lastNameInput.text.toString(),
                emailInput.text.toString().trim(),
                passwordInput.text.toString().trim(),
                1,
                avatarList,
                0,
                500,
                avatarList,
                0
            )

            Log.e("user list", account.toString())

            auth.createUserWithEmailAndPassword(emailInput.text.toString(), passwordInput.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            val uid = it.getResult().user?.uid
                            database.collection("account").document(uid.toString())
                                .set(account).addOnCompleteListener {
                                    if (it.isSuccessful){
                                        Log.e("Success", "added")
                                    } else {
                                        Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                                        Log.e("error", it.exception!!.message.toString())
                                    }
                                }

                            Toast.makeText(this, "Registration Success! ", Toast.LENGTH_SHORT).show()
                            finish()

                        } else {
                            Toast.makeText(this, "Registration failed, please try again! ", Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        loginText.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}