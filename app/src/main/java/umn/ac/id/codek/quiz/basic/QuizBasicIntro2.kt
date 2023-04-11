package umn.ac.id.codek.quiz.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz_basic_intro2.*
import umn.ac.id.codek.R

class QuizBasicIntro2 : Fragment() {
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_basic_intro2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController?.navigate(R.id.action_quizBasicIntro2_to_quizBasicIntro)
                }
            })
    }

    private fun attachInputListener() {
        nextBtnBasicIntro2.setOnClickListener {
            navController?.navigate(R.id.action_quizBasicIntro2_to_quizBasicPage)
        }
        backBtnBasicIntro2.setOnClickListener {
            navController?.navigate(R.id.action_quizBasicIntro2_to_quizBasicIntro)
        }
    }

}