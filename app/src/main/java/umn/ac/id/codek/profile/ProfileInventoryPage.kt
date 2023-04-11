package umn.ac.id.codek.profile

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
import kotlinx.android.synthetic.main.fragment_profile_inventory_page.*
import umn.ac.id.codek.R
import umn.ac.id.codek.auth.Home
import umn.ac.id.codek.avatarShop

class ProfileInventoryPage : Fragment() {
    private var navController: NavController? = null
    private lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    var invList: ArrayList<avatarShop> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_inventory_page, container, false)
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
                    navController?.navigate(R.id.action_profileInventoryPage_to_profileHomePage)
                }
            })

        topAppBar.setNavigationOnClickListener {
            navController?.navigate(R.id.action_profileInventoryPage_to_profileHomePage)
        }
    }

    private fun loadData() {
        val uid = auth.uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            val avatarList = it.get("avatarInv")
            val currAvatar = it.getLong("currAvatar")

            setUpInv(avatarList.toString())
            inventoryListRv.adapter = ProfileInventoryAdapter(invList, currAvatar!!.toInt(), requireActivity(), database, resources)
            inventoryListRv.layoutManager = LinearLayoutManager(requireContext())
            inventoryListRv.setHasFixedSize(true)
        }
    }

    private fun setUpInv(avatarList: String) {
        if (avatarList.contains("1")){
            invList.add(avatarShop(1, R.drawable.avatar_1, 100))
        }
        if (avatarList.contains("2")){
            invList.add(avatarShop(2, R.drawable.avatar_2, 100))
        }
        if (avatarList.contains("3")){
            invList.add(avatarShop(3, R.drawable.avatar_3, 100))
        }
        if (avatarList.contains("4")){
            invList.add(avatarShop(4, R.drawable.avatar_4, 100))
        }
        if (avatarList.contains("5")){
            invList.add(avatarShop(5, R.drawable.avatar_5, 100))
        }
        if (avatarList.contains("6")){
            invList.add(avatarShop(6, R.drawable.avatar_6, 100))
        }
        if (avatarList.contains("7")){
            invList.add(avatarShop(7, R.drawable.avatar_7, 100))
        }
        if (avatarList.contains("8")){
            invList.add(avatarShop(8, R.drawable.avatar_8, 100))
        }
        if (avatarList.contains("9")){
            invList.add(avatarShop(9, R.drawable.avatar_9, 100))
        }
        if (avatarList.contains("10")){
            invList.add(avatarShop(10, R.drawable.avatar_10, 100))
        }
    }


}