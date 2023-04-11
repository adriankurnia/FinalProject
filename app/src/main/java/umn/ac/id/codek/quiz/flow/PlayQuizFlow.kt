package umn.ac.id.codek.quiz.flow

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
import kotlinx.android.synthetic.main.fragment_play_quiz_flow.*
import kotlinx.android.synthetic.main.fragment_quiz_score_page.*
import umn.ac.id.codek.R
import umn.ac.id.codek.questionList

class PlayQuizFlow : Fragment() {

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_quiz_flow, container, false)
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
            if (achievementList.contains("1")) {
                newAchievementList.add(1)
            }
            if (achievementList.contains("2")) {
                newAchievementList.add(2)
            }
            if (achievementList.contains("3")) {
                newAchievementList.add(3)
            }
            if (achievementList.contains("4")) {
                newAchievementList.add(4)
            }
            if (achievementList.contains("5")) {
                newAchievementList.add(5)
            }
        }

        // setting the total point Tv
        totalPointFlow.text = "Point : $currPoint"

        // adding list of question
        questionList.add(
            questionList(
                R.drawable.soal_flow1,
                "1",
                "0",
                "1",
                "2",
                "3"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow2,
                "7",
                "5",
                "7",
                "3",
                "8"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow3,
                "it is am",
                "it is am",
                "am",
                "pm",
                "it is pm"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow4,
                "infinite",
                "0",
                "1",
                "2",
                "infinite"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow5,
                "7",
                "7",
                "8",
                "6",
                "5"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow6,
                "25",
                "20",
                "25",
                "10",
                "15"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow7,
                "3",
                "2",
                "4",
                "5",
                "3"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow8,
                "6",
                "5",
                "6",
                "7",
                "8"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow9,
                "9",
                "10",
                "5",
                "2",
                "9"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow10,
                "6",
                "6",
                "5",
                "7",
                "4"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow11,
                "10",
                "1",
                "9",
                "10",
                "11"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_flow12,
                "35 was found",
                "35",
                "35 not found",
                "35 was found",
                "x was found"
            )
        )

        // set a countdown
        timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownFlow.text = "Countdown : " + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                database.collection("account").document(uid).update(
                    "point", currPoint+point,
                    "coin", coin+currPoint,
                    "tempPoint", currPoint
                )

                optionAFlow.isClickable = false
                optionBFlow.isClickable = false
                optionCFlow.isClickable = false
                optionDFlow.isClickable = false
                navController?.navigate(R.id.action_playQuizFlow_to_quizScorePage)
            }
        }

        // start the quiz
        timer.start()
        newQuestion()

        // setting the button for answer
        optionAFlow.setOnClickListener {
            if (ans == "A"){
                currPoint += 10
                totalPointFlow.text = "Point : $currPoint"
                optionAFlow.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionAFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionAFlow.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionAFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionBFlow.setOnClickListener {
            if (ans == "B"){
                currPoint += 10
                totalPointFlow.text = "Point : $currPoint"
                optionBFlow.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionBFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionBFlow.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionBFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionCFlow.setOnClickListener {
            if (ans == "C"){
                currPoint += 10
                totalPointFlow.text = "Point : $currPoint"
                optionCFlow.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionCFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionCFlow.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionCFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionDFlow.setOnClickListener {
            if (ans == "D"){
                currPoint += 10
                totalPointFlow.text = "Point : $currPoint"
                optionDFlow.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionDFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionDFlow.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionDFlow.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

    }

    private fun newQuestion() {
        val total = questionList.size -1
        val pos = (0..total).random()
        // setting the image question
        questionImageFlow.setImageResource(questionList[pos].question)

        // setting the option answer
        optionATvFlow.text = questionList[pos].optionA
        optionBTvFlow.text = questionList[pos].optionB
        optionCTvFlow.text = questionList[pos].optionC
        optionDTvFlow.text = questionList[pos].optionD

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
            optionAFlow.isClickable = false
            optionBFlow.isClickable = false
            optionCFlow.isClickable = false
            optionDFlow.isClickable = false

            // adding achiev for perfect score
            if(currPoint == 100){
                if (!achievementList.contains("4")){
                    newAchievementList.add(4)
                    database.collection("account").document(auth.uid.toString()).update("achievementList",newAchievementList)
                }
            }

            timer.cancel()
            navController?.navigate(R.id.action_playQuizFlow_to_quizScorePage)
        }
    }
}