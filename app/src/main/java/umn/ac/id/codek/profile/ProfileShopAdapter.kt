package umn.ac.id.codek.profile

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.shop_item.view.*
import umn.ac.id.codek.R
import umn.ac.id.codek.avatarShop

class ProfileShopAdapter (private var itemList: List<avatarShop>,
                          private val coin: TextView,
                          private val profileActivity: Activity,
                          private val database: FirebaseFirestore) : RecyclerView.Adapter<ProfileShopAdapter.ViewHolder>()
{
    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val avatar: ImageView = item.avatarImg
        val coin: TextView = item.coinTv
        val buyBtn: Button = item.buyBtn

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileShopAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileShopAdapter.ViewHolder, position: Int) {
        val currItem = itemList[position]

        holder.avatar.setImageResource(itemList[position].image)
        holder.coin.text = itemList[position].price.toString()

        holder.buyBtn.setOnClickListener {
            purchaseAvatar(position, itemList[position].id, itemList[position].price)
        }
    }

    private fun purchaseAvatar(position: Int, id: Int, price: Int) {
        var newList: ArrayList<Int> = ArrayList()
        var shoplist: ArrayList<avatarShop> = ArrayList()
        var currCoin: Int = 0
        var newCoin: Int = 0
        val uid = FirebaseAuth.getInstance().uid.toString()
        var achievementList = ""
        var newAchievementList: ArrayList<Int> = ArrayList()

        database.collection("account").document(uid).get().addOnSuccessListener {
            val avatarList = it.get("avatarInv")
            currCoin = it.getLong("coin")!!.toInt()
            Log.e("curr coin database", currCoin.toString())

            Log.e("avatar inv", avatarList.toString())

            setNewList(avatarList.toString(), newList, id)

            Log.e("coin", currCoin.toString())
            if (price > currCoin){
                Toast.makeText(profileActivity, "Coin is not enough", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("new list", newList.toString())
                newCoin = currCoin - price
                Log.e("new coin", newCoin.toString())
                database.collection("account").document(uid).update("avatarInv", newList, "coin", newCoin)

                database.collection("account").document(uid).get().addOnSuccessListener {
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

                    setUpShop(avatarList.toString(), shoplist, achievementList, newAchievementList, uid)
                    itemList = shoplist
                    coin.text = it.getLong("coin").toString()
                    notifyDataSetChanged()
                }
            }
        }


    }

    private fun setUpShop(
        list: String,
        shoplist: ArrayList<avatarShop>,
        achievementList: String,
        newAchievementList: ArrayList<Int>,
        uid: String
    ) {
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
                database.collection("account").document(uid).update("achievementList",newAchievementList)
            }
        }

    }

    private fun setNewList(avatarList: String, newList: ArrayList<Int>, id: Int) {
        if (avatarList.contains("1")){
            newList.add(1)
        }
        if (avatarList.contains("2")){
            newList.add(2)
        }
        if (avatarList.contains("3")){
            newList.add(3)
        }
        if (avatarList.contains("4")){
            newList.add(4)
        }
        if (avatarList.contains("5")){
            newList.add(5)
        }
        if (avatarList.contains("6")){
            newList.add(6)
        }
        if (avatarList.contains("7")){
            newList.add(7)
        }
        if (avatarList.contains("8")){
            newList.add(8)
        }
        if (avatarList.contains("9")){
            newList.add(9)
        }
        if (avatarList.contains("10")){
            newList.add(10)
        }
        newList.add(id)
        Log.e("set new list", newList.toString())
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}