package umn.ac.id.codek.leaderboard

import android.content.Intent
import android.os.Bundle
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
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_leaderboard_home_page.*
import kotlinx.android.synthetic.main.fragment_leaderboard_home_page.topAppBar
import umn.ac.id.codek.R
import umn.ac.id.codek.accountLeaderboard
import umn.ac.id.codek.auth.Home
import umn.ac.id.codek.avatarShop

class LeaderboardHomePage : Fragment() {
    private var navController: NavController? = null
    private lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    var leaderboardList: ArrayList<accountLeaderboard> = ArrayList()
    var currName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard_home_page, container, false)
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
            currName = it.getString("firstname").toString()
            database.collection("account").orderBy("point", Query.Direction.DESCENDING)
                .get().addOnSuccessListener {
                for (i in it){
                    leaderboardList.add(accountLeaderboard(
                        i.getString("firstname").toString(),
                        i.getLong("currAvatar")!!.toInt(),
                        i.getLong("point")!!.toInt()))
                }
                leaderboardListRv.adapter = LeaderboardAdapter(leaderboardList, currName, resources)
                leaderboardListRv.layoutManager = LinearLayoutManager(requireContext())
                leaderboardListRv.setHasFixedSize(true)
            }
        }
    }

}