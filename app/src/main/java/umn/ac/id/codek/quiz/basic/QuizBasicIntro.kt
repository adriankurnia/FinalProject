package umn.ac.id.codek.quiz.basic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz_basic_intro.*
import umn.ac.id.codek.R
import umn.ac.id.codek.auth.Home

class QuizBasicIntro : Fragment() {
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_basic_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController?.navigate(R.id.action_quizBasicIntro_to_quizHomePage)
                }
            })
    }

    private fun attachInputListener() {
        backBtnBasicIntro.setOnClickListener{
            navController?.navigate(R.id.action_quizBasicIntro_to_quizHomePage)
        }

        nextBtnBasicIntro.setOnClickListener{
            navController?.navigate(R.id.action_quizBasicIntro_to_quizBasicIntro2)
        }

        quizBtnBasic.setOnClickListener {
            navController?.navigate(R.id.action_quizBasicIntro_to_playQuizBasic)
        }
    }
}