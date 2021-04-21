package com.nick_sib.beauty_radar

object SingletonUID {

    private var instance: SingletonUID? = null

    private val syncObj = Any()

    private var uid: String? = null

    fun setUID(uid: String?) {
        this.uid = uid
    }

    fun getUID(): String? {
        return uid
    }

    fun getInstance(): SingletonUID? {
        synchronized(syncObj) {
            if (instance == null) {
                instance = SingletonUID
            }
            return instance
        }
    }
}