package umn.ac.id.codek.achievement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_achievement_home_page.*
import umn.ac.id.codek.R
import umn.ac.id.codek.accountLeaderboard
import umn.ac.id.codek.achievementData
import umn.ac.id.codek.auth.Home

class AchievementHomePage : Fragment() {
    private var navController: NavController? = null
    private lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    var achievementList: ArrayList<achievementData> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        loadData()

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

    private fun loadData() {
        val uid = auth.uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            val list = it.get("achievementList").toString()

            achievementList.add(achievementData(1, R.drawable.ic_achievement1, "Newcomer", "Join the Gang"))
            achievementList.add(achievementData(2, R.drawable.ic_achievement2, "Collector", "Collect All Avatar"))
            achievementList.add(achievementData(3, R.drawable.ic_achievement3, "Long Way to Go", "Get 100 Point on the First Level"))
            achievementList.add(achievementData(4, R.drawable.ic_achievement4, "Average", "Get 100 Point on the Second Level"))
            achievementList.add(achievementData(5, R.drawable.ic_achievement5, "Completionist", "Get 100 Point on the Third Level"))

            achievementListRv.adapter = AchievementAdapter(achievementList, list, resources)
            achievementListRv.layoutManager = LinearLayoutManager(requireContext())
            achievementListRv.setHasFixedSize(true)
        }
    }
}