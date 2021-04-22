package com.nick_sib.beauty_radar.data.entites

data class Order(
    val id:Int,
    val masterUid:String,
    val clientUid:String,
    val dateTime:Long,
    val service:String,
    val price:Double,
    val duration:Double,
    val status:String

)
