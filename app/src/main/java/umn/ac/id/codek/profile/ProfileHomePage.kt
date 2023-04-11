package umn.ac.id.codek.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_profile_home_page.*
import umn.ac.id.codek.R
import umn.ac.id.codek.auth.Home
import umn.ac.id.codek.auth.Login

class ProfileHomePage : Fragment() {
    private var navController: NavController? = null
    private lateinit var database: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        loadProfile()
        attachInputListener()

        // handling native back button
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startActivity(Intent(activity, Home::class.java))
                }
            })
    }

    private fun loadProfile() {
        val uid = auth.uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            // first name
            firstnameText.text = it.getString("firstname").toString()

            // avatar
            val avatarId = it.getLong("currAvatar").toString()
            setAvatar(avatarId)

            // coin
            coinText.text = it.getLong("coin").toString()
        }
    }

    private fun setAvatar(id: String) {
        if (id == "1"){
            placeholder.setImageResource(R.drawable.avatar_1)
        } else if (id == "2"){
            placeholder.setImageResource(R.drawable.avatar_2)
        } else if (id == "3"){
            placeholder.setImageResource(R.drawable.avatar_3)
        } else if (id == "4"){
            placeholder.setImageResource(R.drawable.avatar_4)
        } else if (id == "5"){
            placeholder.setImageResource(R.drawable.avatar_5)
        } else if (id == "6"){
            placeholder.setImageResource(R.drawable.avatar_6)
        } else if (id == "7"){
            placeholder.setImageResource(R.drawable.avatar_7)
        } else if (id == "8"){
            placeholder.setImageResource(R.drawable.avatar_8)
        } else if (id == "9"){
            placeholder.setImageResource(R.drawable.avatar_9)
        } else if (id == "10"){
            placeholder.setImageResource(R.drawable.avatar_10)
        }
    }

    private fun attachInputListener() {
        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(activity, Login::class.java))
        }

        shopBtn.setOnClickListener {
            navController?.navigate(R.id.action_profileHomePage_to_profileShopPage)
        }

        inventoryBtn.setOnClickListener {
            navController?.navigate(R.id.action_profileHomePage_to_profileInventoryPage)
        }

        topAppBar.setNavigationOnClickListener {
            startActivity(Intent(activity, Home::class.java))
        }
    }

}