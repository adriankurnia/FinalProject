package umn.ac.id.codek.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import umn.ac.id.codek.R
import umn.ac.id.codek.achievement.AchievementHomeActivity
import umn.ac.id.codek.leaderboard.LeaderboardHomeActivity
import umn.ac.id.codek.profile.Profile
import umn.ac.id.codek.quiz.QuizHomeActivity

class Home : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
/*    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //HIDE ACTION BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
/*        database = FirebaseDatabase.getInstance("https://codek-1265e-default-rtdb.asia-southeast1.firebasedatabase.app")
        databaseReference = database?.reference!!.child("profile")*/

        //loadProfile()
        attachInputListeners()
    }

    override fun onBackPressed() {

    }

    private fun attachInputListeners() {
        quizBtn.setOnClickListener {
            startActivity(Intent(this, QuizHomeActivity::class.java))
        }

        achievementBtn.setOnClickListener {
            startActivity(Intent(this, AchievementHomeActivity::class.java))
        }

        leaderboardBtn.setOnClickListener {
            startActivity(Intent(this, LeaderboardHomeActivity::class.java))
        }

        profileBtn.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }

/*    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                titleTv.text = "Welcome to Code:K\n" +  snapshot.child("firstname").value.toString() + "!"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }*/
}