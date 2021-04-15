package com.nick_sib.beauty_radar.provider.profile.entities

data class UserProfile(
    val uid: String,
    val name: String? = null,
    val phone: String? = null,
    val birthday: String? = null)
//    val job: String,
//    val visitToTheClient: String,
//    val experience: String,
//    val specialization: String,
//    val services: String,
//    val visitReminder: String,
//    val schedule: String,
//    val breaks:String)