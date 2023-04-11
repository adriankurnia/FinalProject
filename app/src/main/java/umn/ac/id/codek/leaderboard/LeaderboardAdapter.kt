package umn.ac.id.codek.leaderboard

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.leaderboard_item.view.*
import umn.ac.id.codek.R
import umn.ac.id.codek.accountLeaderboard
import umn.ac.id.codek.profile.ProfileShopAdapter

class LeaderboardAdapter(private var list: List<accountLeaderboard>,
                         private var currName: String,
                         private val resources: Resources
                         ): RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val avatar: ImageView = item.avatarImgLeaderboard
        val point: TextView = item.poinTv
        val accountname: TextView = item.namaTv
        val rank: TextView = item.positionTv
        val cardView: CardView = item.cardView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderboardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        return LeaderboardAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardAdapter.ViewHolder, position: Int) {
        holder.rank.text = (position + 1).toString()
        holder.accountname.text = list[position].firstname
        if (currName == list[position].firstname){
            // user account
            holder.cardView.setBackgroundColor(resources.getColor(R.color.Poweder_blue))
        } else {
            holder.cardView.setBackgroundColor(resources.getColor(R.color.Prussian_blue))
        }
        holder.point.text = list[position].point.toString()
        setUpAvatar(position, holder)
    }

    private fun setUpAvatar(position: Int, holder: LeaderboardAdapter.ViewHolder) {
        if (list[position].currAvatar == 1){
            holder.avatar.setImageResource(R.drawable.avatar_1)
        } else if (list[position].currAvatar == 2){
            holder.avatar.setImageResource(R.drawable.avatar_2)
        } else if (list[position].currAvatar == 3){
            holder.avatar.setImageResource(R.drawable.avatar_3)
        } else if (list[position].currAvatar == 4){
            holder.avatar.setImageResource(R.drawable.avatar_4)
        } else if (list[position].currAvatar == 5){
            holder.avatar.setImageResource(R.drawable.avatar_5)
        } else if (list[position].currAvatar == 6){
            holder.avatar.setImageResource(R.drawable.avatar_6)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}