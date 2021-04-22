package com.nick_sib.beauty_radar.provider.calendar.entities

class CalendarProfile {

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

}