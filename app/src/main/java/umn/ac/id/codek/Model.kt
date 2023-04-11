package umn.ac.id.codek

data class account(
    var firstname: String,
    var lastname: String,
    var email: String,
    var password: String,
    var currAvatar: Int,
    var avatarInv: List<Int>,
    var point: Int,
    var coin: Int,
    var achievementList: List<Int>,
    var tempPoint: Int
)

data class avatarShop(
    val id: Int,
    val image: Int,
    val price: Int
)

data class avatarList(
    var ownedAvatar: ArrayList<Int>
)

data class accountLeaderboard(
    var firstname: String,
    var currAvatar: Int,
    var point: Int
)

data class achievementData(
    val id: Int,
    val image: Int,
    val title: String,
    val desc: String
)

data class questionList(
    val question: Int,
    val correctAns: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String
)