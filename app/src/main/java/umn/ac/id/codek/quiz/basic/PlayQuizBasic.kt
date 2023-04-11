package umn.ac.id.codek.quiz.basic

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_play_quiz_basic.*
import kotlinx.android.synthetic.main.fragment_profile_home_page.*
import umn.ac.id.codek.R
import umn.ac.id.codek.questionList

class PlayQuizBasic : Fragment() {

    private var navController: NavController? = null
    private lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    private lateinit var timer: CountDownTimer

    var coin = 0
    var point = 0
    var currPoint = 0

    var questionList: ArrayList<questionList> = ArrayList()
    var ans = ""
    var questionNo = 0

    var achievementList = ""
    var newAchievementList: ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_quiz_basic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })

        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        val uid = auth.uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            // coin
            coin = it.getLong("coin")!!.toInt()
            // point
            point = it.getLong("point")!!.toInt()
            // achievement
            achievementList = it.get("achievementList").toString()
            if (achievementList.contains("1")){
                newAchievementList.add(1)
            }
            if (achievementList.contains("2")){
                newAchievementList.add(2)
            }
            if (achievementList.contains("3")){
                newAchievementList.add(3)
            }
            if (achievementList.contains("4")){
                newAchievementList.add(4)
            }
            if (achievementList.contains("5")){
                newAchievementList.add(5)
            }
        }
        // setting the total point Tv
        totalPoint.text = "Point : $currPoint"

        // adding list of question
        questionList.add(
            questionList(
                R.drawable.soal_basic1,
                "Kabizar",
                "Kabizar",
                "Putri",
                "String",
                "Error"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_basic2,
                "val pi: Float = 3.14f",
                "var pi = 3.14f",
                "pi = 3.14f",
                "val float: pi = 3.14f",
                "val pi: Float = 3.14f"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_basic3,
                "Galuh",
                "Galuh",
                "String",
                "ContactName",
                "Array"
            )
        )
        questionList.add(questionList(R.drawable.soal_basic4, "Error", "55", "50", "40", "Error"))
        questionList.add(questionList(R.drawable.soal_basic5, "var", "val", "var", "fun", "if"))
        questionList.add(
            questionList(
                R.drawable.soal_basic6,
                "Float",
                "Int",
                "String",
                "Float",
                "Boolean"
            )
        )
        questionList.add(questionList(R.drawable.soal_basic7, "20", "10", "2", "5", "20"))
        questionList.add(questionList(R.drawable.soal_basic8, "5", "5", "10", "50", "15"))
        questionList.add(questionList(R.drawable.soal_basic9, "13", "13", "12", "11", "10"))
        questionList.add(questionList(R.drawable.soal_basic10, "20", "21", "20", "22", "23"))
        questionList.add(questionList(R.drawable.soal_basic11, "6", "6", "7", "2", "4"))
        questionList.add(
            questionList(
                R.drawable.soal_basic12,
                "x--",
                "x++",
                "y--",
                "x = x/2",
                "x--"
            )
        )

        // set a countdown
        timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdown.text = "Countdown : " + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                database.collection("account").document(uid).update(
                    "point", currPoint+point,
                    "coin", coin+currPoint,
                    "tempPoint", currPoint
                )

                optionA.isClickable = false
                optionB.isClickable = false
                optionC.isClickable = false
                optionD.isClickable = false
                navController?.navigate(R.id.action_playQuizBasic_to_quizScorePage)
            }
        }

        // start the quiz
        timer.start()
        newQuestion()

        // setting the button for answer
        optionA.setOnClickListener {
            if (ans == "A"){
                currPoint += 10
                totalPoint.text = "Point : $currPoint"
                optionA.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionA.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionA.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionA.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionB.setOnClickListener {
            if (ans == "B"){
                currPoint += 10
                totalPoint.text = "Point : $currPoint"
                optionB.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionB.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionB.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionB.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionC.setOnClickListener {
            if (ans == "C"){
                currPoint += 10
                totalPoint.text = "Point : $currPoint"
                optionC.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionC.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionC.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionC.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionD.setOnClickListener {
            if (ans == "D"){
                currPoint += 10
                totalPoint.text = "Point : $currPoint"
                optionD.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionD.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionD.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionD.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }
    }

    private fun newQuestion(){
        val total = questionList.size -1
        val pos = (0..total).random()
        // setting the image question
        questionImage.setImageResource(questionList[pos].question)

        // setting the option answer
        optionATv.text = questionList[pos].optionA
        optionBTv.text = questionList[pos].optionB
        optionCTv.text = questionList[pos].optionC
        optionDTv.text = questionList[pos].optionD

        // check which answer is correct
        if (questionList[pos].optionA == questionList[pos].correctAns){
            ans = "A"
        } else if (questionList[pos].optionB == questionList[pos].correctAns){
            ans = "B"
        } else if (questionList[pos].optionC == questionList[pos].correctAns){
            ans = "C"
        } else if (questionList[pos].optionD == questionList[pos].correctAns){
            ans = "D"
        }

        // to stop the quiz when there are alr 10 question
        questionNo++
        questionList.removeAt(pos)
        if (questionNo > 10){
            database.collection("account").document(auth.uid.toString()).update(
                "point", currPoint+point,
                "coin", coin+currPoint,
                "tempPoint", currPoint
            )
            optionA.isClickable = false
            optionB.isClickable = false
            optionC.isClickable = false
            optionD.isClickable = false

            // adding achiev for perfect score
            if(currPoint == 100){
                if (!achievementList.contains("3")){
                    newAchievementList.add(3)
                    database.collection("account").document(auth.uid.toString()).update("achievementList",newAchievementList)
                }
            }

            timer.cancel()
            navController?.navigate(R.id.action_playQuizBasic_to_quizScorePage)
        }
    }
}