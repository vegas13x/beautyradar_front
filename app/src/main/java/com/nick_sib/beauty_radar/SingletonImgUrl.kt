package com.nick_sib.beauty_radar

object SingletonImgUrl {

    private var imgUrl: String? = null

    fun setImgUrl(imgUrl: String?) {
        this.imgUrl = imgUrl
    }

    fun getImgUrl(): String? {
        return imgUrl
    }
}
