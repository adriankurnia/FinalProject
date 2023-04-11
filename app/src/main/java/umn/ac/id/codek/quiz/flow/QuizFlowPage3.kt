package umn.ac.id.codek.quiz.flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz_flow_page3.*
import umn.ac.id.codek.R

class QuizFlowPage3 : Fragment() {
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_flow_page3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController?.navigate(R.id.action_quizFlowPage3_to_quizFlowPage2)
                }
            })
    }

    private fun attachInputListener() {
        nextBtnFlow3.setOnClickListener {
            navController?.navigate(R.id.action_quizFlowPage3_to_quizFlowPage4)
        }

        backBtnFlow3.setOnClickListener {
            navController?.navigate(R.id.action_quizFlowPage3_to_quizFlowPage2)
        }
    }
}