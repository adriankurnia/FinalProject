package umn.ac.id.codek.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_achievement_home_page.*
import kotlinx.android.synthetic.main.fragment_quiz_home_page.*
import kotlinx.android.synthetic.main.fragment_quiz_home_page.topAppBar
import umn.ac.id.codek.R
import umn.ac.id.codek.auth.Home
import umn.ac.id.codek.databinding.FragmentQuizHomePageBinding

class QuizHomePage : Fragment() {
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startActivity(Intent(activity, Home::class.java))
                }
            })

        topAppBar.setNavigationOnClickListener {
            startActivity(Intent(activity, Home::class.java))
        }
    }

    private fun attachInputListener() {
        basicBtn.setOnClickListener {
            navController?.navigate(R.id.action_quizHomePage_to_quizBasicIntro)
        }

        flowBtn.setOnClickListener {
            navController?.navigate(R.id.action_quizHomePage_to_quizFlowPage)

        }

        functionBtn.setOnClickListener {
            navController?.navigate(R.id.action_quizHomePage_to_quizFunctionPage)
        }
    }

}