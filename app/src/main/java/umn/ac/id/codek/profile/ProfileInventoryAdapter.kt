package umn.ac.id.codek.profile

import android.app.Activity
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.shop_item.view.*
import umn.ac.id.codek.R
import umn.ac.id.codek.avatarShop

class ProfileInventoryAdapter(
    private var invList: List<avatarShop>,
    private var currAvatar: Int,
    private val profileInventoryActivity: Activity,
    private val database: FirebaseFirestore,
    private val resources: Resources
) : RecyclerView.Adapter<ProfileInventoryAdapter.ViewHolder>() {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val avatar: ImageView = item.avatarImg
        val equipBtn: Button = item.buyBtn
        val coinTv: TextView = item.coinTv
        val coinLogo: ImageView = item.coinLogo
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileInventoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ProfileInventoryAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileInventoryAdapter.ViewHolder, position: Int) {
        holder.coinLogo.visibility = View.INVISIBLE
        holder.coinTv.visibility = View.INVISIBLE
        //holder.avatar.setImageResource(invList[position].image)
        //holder.equipBtn.text = "Equip"

        val uid = FirebaseAuth.getInstance().uid.toString()
        database.collection("account").document(uid).get().addOnSuccessListener {
            val currAvatar = it.getLong("currAvatar")!!.toInt()
            if (currAvatar == invList[position].id){
                holder.equipBtn.text = "Used"
                holder.equipBtn.setTextColor(resources.getColor(R.color.black))
            } else {
                holder.equipBtn.text = "Set"
                holder.equipBtn.setTextColor(resources.getColor(R.color.white))
            }
        }

        setUpInventory(holder, position)

        holder.equipBtn.setOnClickListener {
            database.collection("account").document(uid).update("currAvatar", invList[position].id)
            currAvatar = position + 1
            notifyDataSetChanged()
        }
    }

    private fun setUpInventory(holder: ProfileInventoryAdapter.ViewHolder, position: Int) {
        if (invList[position].id == 1){
            holder.avatar.setImageResource(R.drawable.avatar_1)
        }
        if (invList[position].id == 2){
            holder.avatar.setImageResource(R.drawable.avatar_2)
        }
        if (invList[position].id == 3){
            holder.avatar.setImageResource(R.drawable.avatar_3)
        }
        if (invList[position].id == 4){
            holder.avatar.setImageResource(R.drawable.avatar_4)
        }
        if (invList[position].id == 5){
            holder.avatar.setImageResource(R.drawable.avatar_5)
        }
        if (invList[position].id == 6){
            holder.avatar.setImageResource(R.drawable.avatar_6)
        }
        if (invList[position].id == 7){
            holder.avatar.setImageResource(R.drawable.avatar_7)
        }
        if (invList[position].id == 8){
            holder.avatar.setImageResource(R.drawable.avatar_8)
        }
        if (invList[position].id == 9){
            holder.avatar.setImageResource(R.drawable.avatar_9)
        }
        if (invList[position].id == 10){
            holder.avatar.setImageResource(R.drawable.avatar_10)
        }
    }

    override fun getItemCount(): Int {
        return invList.size
    }
}