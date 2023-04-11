package umn.ac.id.codek.quiz

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
import kotlinx.android.synthetic.main.fragment_play_quiz_function.*
import umn.ac.id.codek.R
import umn.ac.id.codek.questionList

class PlayQuizFunction : Fragment() {

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
        return inflater.inflate(R.layout.fragment_play_quiz_function, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        totalPointFunc.text = "Point : $currPoint"

        questionList.add(
            questionList(
                R.drawable.soal_func1,
                "printHello()",
                "Hello",
                "Fun printHello",
                "printHello",
                "printHello()"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func2,
                "sum(3,5)",
                "sum()",
                "fun sum(3, 5)",
                "sum = 3 + 5",
                "sum(3,5)"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func3,
                "10",
                "5",
                "15",
                "20",
                "10"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func4,
                "fun",
                "val",
                "fun",
                "var",
                "if"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func5,
                "String",
                "Int",
                "Float",
                "String",
                "Boolean"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func6,
                "printName(“james“, “watson“)",
                "printName(“james“, “watson“)",
                "printName()",
                "printName(“watson”)",
                "Fun printName(“james”,”watson”)"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func7,
                "Int",
                "Int",
                "Float",
                "String",
                "Boolean"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func8,
                "=",
                "=",
                "==",
                "+",
                "-"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func9,
                "Int",
                "Int",
                "Float",
                "String",
                "Boolean"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func10,
                "false",
                "false",
                "true",
                "allowed",
                "not allowed"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func11,
                "multiply(10,10)",
                "Fun Multiply ()",
                "Fun multiply(10,10)",
                "multiply()",
                "multiply(10,10)"
            )
        )
        questionList.add(
            questionList(
                R.drawable.soal_func12,
                "Boolean",
                "Int",
                "Float",
                "String",
                "Boolean"
            )
        )

        // set a countdown
        timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownFunc.text = "Countdown : " + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                database.collection("account").document(uid).update(
                    "point", currPoint+point,
                    "coin", coin+currPoint,
                    "tempPoint", currPoint
                )

                optionAFunc.isClickable = false
                optionBFunc.isClickable = false
                optionCFunc.isClickable = false
                optionDFunc.isClickable = false
                navController?.navigate(R.id.action_playQuizFunction_to_quizScorePage)
            }
        }

        // start the quiz
        timer.start()
        newQuestion()

        // setting the button for answer
        optionAFunc.setOnClickListener {
            if (ans == "A"){
                currPoint += 10
                totalPointFunc.text = "Point : $currPoint"
                optionAFunc.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionAFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionAFunc.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionAFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionBFunc.setOnClickListener {
            if (ans == "B"){
                currPoint += 10
                totalPointFunc.text = "Point : $currPoint"
                optionBFunc.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionBFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionBFunc.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionBFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionCFunc.setOnClickListener {
            if (ans == "C"){
                currPoint += 10
                totalPointFunc.text = "Point : $currPoint"
                optionCFunc.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionCFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionCFunc.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionCFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        optionDFunc.setOnClickListener {
            if (ans == "D"){
                currPoint += 10
                totalPointFunc.text = "Point : $currPoint"
                optionDFunc.setBackgroundResource(R.drawable.button_green)
                Handler().postDelayed({
                    optionDFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            } else {
                optionDFunc.setBackgroundResource(R.drawable.button_red)
                Handler().postDelayed({
                    optionDFunc.setBackgroundResource(R.drawable.button_white)
                    newQuestion()
                }, 500)
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })
    }

    private fun newQuestion() {
        val total = questionList.size -1
        val pos = (0..total).random()


        // setting the image question
        questionImageFunc.setImageResource(questionList[pos].question)

        // setting the option answer
        optionATvFunc.text = questionList[pos].optionA
        optionBTvFunc.text = questionList[pos].optionB
        optionCTvFunc.text = questionList[pos].optionC
        optionDTvFunc.text = questionList[pos].optionD

        // setting the right answer
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
            optionAFunc.isClickable = false
            optionBFunc.isClickable = false
            optionCFunc.isClickable = false
            optionDFunc.isClickable = false

            // adding achiev for perfect score
            if(currPoint == 100){
                if (!achievementList.contains("5")){
                    newAchievementList.add(5)
                    database.collection("account").document(auth.uid.toString()).update("achievementList",newAchievementList)
                }
            }

            timer.cancel()
            navController?.navigate(R.id.action_playQuizFunction_to_quizScorePage)
        }
    }

}