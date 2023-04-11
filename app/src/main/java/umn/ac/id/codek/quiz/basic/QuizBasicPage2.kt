package umn.ac.id.codek.quiz.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz_basic_page.*
import kotlinx.android.synthetic.main.fragment_quiz_basic_page.nextBtn
import kotlinx.android.synthetic.main.fragment_quiz_basic_page2.*
import umn.ac.id.codek.R

class QuizBasicPage2 : Fragment() {
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_basic_page2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController?.navigate(R.id.action_quizBasicPage2_to_quizBasicPage)
                }
            })
    }

    private fun attachInputListener() {
        nextBtn2.setOnClickListener {
            navController?.navigate(R.id.action_quizBasicPage2_to_playQuizBasic)
        }

        backBtn2.setOnClickListener {
            navController?.navigate(R.id.action_quizBasicPage2_to_quizBasicPage)
        }
    }
}