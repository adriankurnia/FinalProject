package umn.ac.id.codek.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile_home_page.coinText
import kotlinx.android.synthetic.main.fragment_profile_shop_page.*
import umn.ac.id.codek.R
import umn.ac.id.codek.auth.Home
import umn.ac.id.codek.avatarList
import umn.ac.id.codek.avatarShop

class ProfileShopPage : Fragment() {
    private var navController: NavController? = null
    private lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    var shoplist: ArrayList<avatarShop> = ArrayList()
    var achievementList = ""
    var newAchievementList: ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_shop_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        loadData()

        // handling native back button
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController?.navigate(R.id.action_profileShopPage_to_profileHomePage)
                }
            })

        topAppBar.setNavigationOnClickListener {
            navController?.navigate(R.id.action_profileShopPage_to_profileHomePage)
        }
    }

    private fun loadData() {
        val uid = auth.uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            coinText.text = it.getLong("coin").toString()
            val avatarList = it.get("avatarInv")
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
            setUpShop(avatarList.toString())
            Log.e("shopList", shoplist.toString())
            shopListRv.adapter = ProfileShopAdapter(shoplist, coinText, requireActivity(), database )
            shopListRv.layoutManager = LinearLayoutManager(requireContext())
            shopListRv.setHasFixedSize(true)

        }
    }

    private fun setUpShop(list: String) {
        if(!list.contains("2")){
            shoplist.add(avatarShop(2, R.drawable.avatar_2, 100))
        }
        if(!list.contains("3")){
            shoplist.add(avatarShop(3, R.drawable.avatar_3, 100))
        }
        if(!list.contains("4")){
            shoplist.add(avatarShop(4, R.drawable.avatar_4, 100))
        }
        if(!list.contains("5")){
            shoplist.add(avatarShop(5, R.drawable.avatar_5, 100))
        }
        if(!list.contains("6")){
            shoplist.add(avatarShop(6, R.drawable.avatar_6, 100))
        }
        if(!list.contains("7")){
            shoplist.add(avatarShop(7, R.drawable.avatar_7, 100))
        }
        if(!list.contains("8")){
            shoplist.add(avatarShop(8, R.drawable.avatar_8, 100))
        }
        if(!list.contains("9")){
            shoplist.add(avatarShop(9, R.drawable.avatar_9, 100))
        }
        if(!list.contains("10")){
            shoplist.add(avatarShop(10, R.drawable.avatar_10, 100))
        }
        if (shoplist.toString() == "[]"){
            if (!achievementList.contains("2")){
                newAchievementList.add(2)
                database.collection("account").document(auth.uid.toString()).update("achievementList",newAchievementList)
            }
        }
    }

}