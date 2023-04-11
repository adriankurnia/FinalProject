package umn.ac.id.codek.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_quiz_function_page.*
import umn.ac.id.codek.R

class QuizFunctionPage : Fragment() {

    private var navController: NavController? = null
    var phaseState = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_function_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        attachInputListener()

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (phaseState == 1){
                        navController?.navigate(R.id.action_quizFunctionPage_to_quizHomePage)
                    } else if (phaseState == 2){
                        //pindah halaman 1
                        pageTv.text = "1/5"

                        ImagePhase1_1.visibility = View.VISIBLE
                        ImagePhase1_2.visibility = View.VISIBLE
                        quizBtnFunction.visibility = View.VISIBLE
                        ImagePhase2_1.visibility = View.GONE
                        ImagePhase2_2.visibility = View.GONE

                        TvTittlePhase1.visibility = View.VISIBLE
                        TvPhase1.visibility = View.VISIBLE
                        TvTittlePhase2.visibility = View.GONE
                        TvPhase2.visibility = View.GONE
                        phaseState = 1
                    } else if (phaseState == 3){
                        //pindah halaman 2
                        pageTv.text = "2/5"

                        ImagePhase2_1.visibility = View.VISIBLE
                        ImagePhase2_2.visibility = View.VISIBLE
                        ImagePhase3_1.visibility = View.GONE
                        ImagePhase3_2.visibility = View.GONE

                        TvTittlePhase2.visibility = View.VISIBLE
                        TvPhase2.visibility = View.VISIBLE
                        TvTittlePhase3.visibility = View.GONE
                        TvPhase3.visibility = View.GONE
                        phaseState = 2
                    } else if (phaseState == 4){
                        //pindah halaman 3
                        pageTv.text = "3/5"

                        ImagePhase3_1.visibility = View.VISIBLE
                        ImagePhase3_2.visibility = View.VISIBLE
                        ImagePhase4_1.visibility = View.GONE

                        TvTittlePhase3.visibility = View.VISIBLE
                        TvPhase3.visibility = View.VISIBLE
                        TvTittlePhase4.visibility = View.GONE
                        TvPhase4.visibility = View.GONE
                        phaseState = 3
                    } else if (phaseState == 5){
                        //pindah halaman 4
                        pageTv.text = "4/5"

                        ImagePhase4_1.visibility = View.VISIBLE
                        ImagePhase4_2.visibility = View.GONE
                        phaseState = 4
                    }
                }
            })
    }

    private fun attachInputListener() {
        quizBtnFunction.setOnClickListener {
            navController?.navigate(R.id.action_quizFunctionPage_to_playQuizFunction)
        }

        nextBtnFunction.setOnClickListener {
            if (phaseState == 1){
                //pindah halaman 2
                pageTv.text = "2/5"
                ImagePhase1_1.visibility = View.INVISIBLE
                ImagePhase1_2.visibility = View.INVISIBLE
                quizBtnFunction.visibility = View.INVISIBLE
                ImagePhase2_1.visibility = View.VISIBLE
                ImagePhase2_2.visibility = View.VISIBLE

                TvTittlePhase1.visibility = View.GONE
                TvPhase1.visibility = View.GONE
                TvTittlePhase2.visibility = View.VISIBLE
                TvPhase2.visibility = View.VISIBLE
                phaseState = 2
            } else if (phaseState == 2){
                //pindah halaman 3
                pageTv.text = "3/5"
                ImagePhase2_1.visibility = View.GONE
                ImagePhase2_2.visibility = View.GONE
                ImagePhase3_1.visibility = View.VISIBLE
                ImagePhase3_2.visibility = View.VISIBLE

                TvTittlePhase2.visibility = View.GONE
                TvPhase2.visibility = View.GONE
                TvTittlePhase3.visibility = View.VISIBLE
                TvPhase3.visibility = View.VISIBLE
                phaseState = 3
            } else if (phaseState == 3) {
                //pindah halaman 4
                pageTv.text = "4/5"
                ImagePhase3_1.visibility = View.GONE
                ImagePhase3_2.visibility = View.GONE
                ImagePhase4_1.visibility = View.VISIBLE

                TvTittlePhase3.visibility = View.GONE
                TvPhase3.visibility = View.GONE
                TvTittlePhase4.visibility = View.VISIBLE
                TvPhase4.visibility = View.VISIBLE
                phaseState = 4
            } else if (phaseState == 4){
                //pindah halaman 5
                pageTv.text = "5/5"
                ImagePhase4_1.visibility = View.GONE
                ImagePhase4_2.visibility = View.VISIBLE
                phaseState = 5
            } else if (phaseState == 5){
                navController?.navigate(R.id.action_quizFunctionPage_to_playQuizFunction)
                Toast.makeText(activity, "Go to Quiz", Toast.LENGTH_SHORT).show()
            }
        }

        backBtnFunction.setOnClickListener {
            if (phaseState == 1){
                navController?.navigate(R.id.action_quizFunctionPage_to_quizHomePage)
            } else if (phaseState == 2){
                //pindah halaman 1
                pageTv.text = "1/5"

                ImagePhase1_1.visibility = View.VISIBLE
                ImagePhase1_2.visibility = View.VISIBLE
                quizBtnFunction.visibility = View.VISIBLE
                ImagePhase2_1.visibility = View.GONE
                ImagePhase2_2.visibility = View.GONE

                TvTittlePhase1.visibility = View.VISIBLE
                TvPhase1.visibility = View.VISIBLE
                TvTittlePhase2.visibility = View.GONE
                TvPhase2.visibility = View.GONE
                phaseState = 1
            } else if (phaseState == 3){
                //pindah halaman 2
                pageTv.text = "2/5"

                ImagePhase2_1.visibility = View.VISIBLE
                ImagePhase2_2.visibility = View.VISIBLE
                ImagePhase3_1.visibility = View.GONE
                ImagePhase3_2.visibility = View.GONE

                TvTittlePhase2.visibility = View.VISIBLE
                TvPhase2.visibility = View.VISIBLE
                TvTittlePhase3.visibility = View.GONE
                TvPhase3.visibility = View.GONE
                phaseState = 2
            } else if (phaseState == 4){
                //pindah halaman 3
                pageTv.text = "3/5"

                ImagePhase3_1.visibility = View.VISIBLE
                ImagePhase3_2.visibility = View.VISIBLE
                ImagePhase4_1.visibility = View.GONE

                TvTittlePhase3.visibility = View.VISIBLE
                TvPhase3.visibility = View.VISIBLE
                TvTittlePhase4.visibility = View.GONE
                TvPhase4.visibility = View.GONE
                phaseState = 3
            } else if (phaseState == 5){
                //pindah halaman 4
                pageTv.text = "4/5"

                ImagePhase4_1.visibility = View.VISIBLE
                ImagePhase4_2.visibility = View.GONE
                phaseState = 4
            }
        }
    }

}