package umn.ac.id.codek.achievement

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.achievement_item.view.*
import kotlinx.android.synthetic.main.leaderboard_item.view.*
import kotlinx.android.synthetic.main.leaderboard_item.view.cardView
import umn.ac.id.codek.R
import umn.ac.id.codek.accountLeaderboard
import umn.ac.id.codek.achievementData
import umn.ac.id.codek.leaderboard.LeaderboardAdapter

class AchievementAdapter (private var list: List<achievementData>,
                          private var ownedAchivement: String,
                          private val resources: Resources
                        ): RecyclerView.Adapter<AchievementAdapter.ViewHolder>() {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val logo: ImageView = item.avatarImgAchievement
        val title: TextView = item.titleTv
        val desc: TextView = item.descTv
        val card: CardView = item.cardViewAchievement
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AchievementAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.achievement_item, parent, false)
        return AchievementAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AchievementAdapter.ViewHolder, position: Int) {
        // unlocked
        holder.title.text = list[position].title
        holder.desc.text = list[position].desc
        holder.logo.setImageResource(list[position].image)

        // locked
        if (!ownedAchivement.contains(list[position].id.toString())){
            holder.card.setBackgroundColor(resources.getColor(R.color.gray))
            holder.title.setTextColor(resources.getColor(R.color.black))
            holder.desc.setTextColor(resources.getColor(R.color.black))
            holder.logo.setColorFilter(resources.getColor(R.color.black))
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}