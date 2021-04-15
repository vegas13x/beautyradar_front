package com.nick_sib.beauty_radar.provider.profile.entities

class CalendareProfile {

    var uid: String? = null
    var name: String? = null
    var dateStart: String? = null
    var dateEnd: String? = null

    constructor()
    constructor(uid: String?, name: String?, dateStart: String?, dateEnd: String?) {
        this.uid = uid
        this.name = name
        this.dateStart = dateStart
        this.dateEnd = dateEnd
    }

    override fun toString(): String {
        return "CalendareProfile(uid=$uid, name=$name, dateStart=$dateStart, dateEnd=$dateEnd)"
    }


}