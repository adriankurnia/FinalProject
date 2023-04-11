package umn.ac.id.codek.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*
import umn.ac.id.codek.R
import umn.ac.id.codek.auth.Login

class Profile : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //HIDE ACTION BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_profile)

        /*// get firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()*/

        //loadProfile()
    }

/*    private fun loadProfile(){
*//*        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)*//*

        loadData()

        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        shopBtn.setOnClickListener {
            Toast.makeText(this, "Go to Shop", Toast.LENGTH_SHORT).show()
        }

        inventoryBtn.setOnClickListener {
            Toast.makeText(this, "Go to Inventory", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        val uid = auth.uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            // first name
            firstnameText.text = it.getString("firstname").toString()

            // avatar
            val avatarId = it.getLong("currAvatar").toString()
            setAvatar(avatarId)

            // coin
            coinText.text = it.getLong("coin").toString()
        }
    }

    private fun setAvatar(id: String) {
        if (id == "1"){
            placeholder.setImageResource(R.drawable.avatar_1)
        } else if (id == "2"){
            placeholder.setImageResource(R.drawable.avatar_2)
        } else if (id == "3"){
            placeholder.setImageResource(R.drawable.avatar_3)
        } else if (id == "4"){
            placeholder.setImageResource(R.drawable.avatar_4)
        } else if (id == "5"){
            placeholder.setImageResource(R.drawable.avatar_5)
        } else if (id == "6"){
            placeholder.setImageResource(R.drawable.avatar_6)
        }

    }*/
}