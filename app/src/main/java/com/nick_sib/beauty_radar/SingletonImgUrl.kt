package com.nick_sib.beauty_radar

object SingletonImgUrl {

    private var instance: SingletonImgUrl? = null

    private val syncObj = Any()

    private var imgUrl: String? = null

    fun setImgUrl(imgUrl: String?) {
        this.imgUrl = imgUrl
    }

    fun getImgUrl(): String? {
        return imgUrl
    }

    fun getInstance(): SingletonImgUrl {
        if (instance == null) {
            synchronized(syncObj) {
                if (instance == null) {
                    instance = SingletonImgUrl
                }
            }
        }
        return instance!!
    }
}
