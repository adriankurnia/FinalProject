package umn.ac.id.codek.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz_flow_page.*
import umn.ac.id.codek.R

class QuizFlowPage : Fragment() {
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_flow_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController?.navigate(R.id.action_quizFlowPage_to_quizHomePage)
                }
            })
    }

    private fun attachInputListener() {
        nextBtnFlow.setOnClickListener {
            navController?.navigate(R.id.action_quizFlowPage_to_quizFlowPage2)
        }
        backBtnFlow.setOnClickListener {
            navController?.navigate(R.id.action_quizFlowPage_to_quizHomePage)
        }
        quizBtnFlow.setOnClickListener {
            navController?.navigate(R.id.action_quizFlowPage_to_playQuizFlow2)
        }
    }

}