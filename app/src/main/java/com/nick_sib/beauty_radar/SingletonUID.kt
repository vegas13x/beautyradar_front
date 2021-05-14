package com.nick_sib.beauty_radar

object SingletonUID {

    private var uid: String? = null

    fun setUID(uid: String?) {
        this.uid = uid
    }

    fun getUID(): String? {
        return uid
    }
}
