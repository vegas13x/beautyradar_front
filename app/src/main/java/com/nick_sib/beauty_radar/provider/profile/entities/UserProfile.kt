package com.nick_sib.beauty_radar.provider.profile.entities

class UserProfile{

    var uid: String? = null
    var name: String? = null
    var secondName: String? = null
    var master: Boolean = false
    var client: Boolean = false
    var phone: String? = null
    var birthday: String? = null
    var visitToTheClient: String? = null
    var experience: String? = null
    var specialization: String? = null
    var services: String? = null
    var visitReminder: String? = null
    var schedule: String? = null
    var breaks: String? = null


    constructor()
    constructor(uid: String?, name: String?,secondName: String?, phone: String?, birthday: String?,
                master: Boolean, client: Boolean, visitToTheClient: String?, experience: String?,
                specialization: String?, services: String?, visitReminder: String?,
                schedule: String?, breaks: String?) {
        this.uid = uid
        this.name = name
        this.secondName = secondName
        this.phone = phone
        this.birthday = birthday
        this.master = master
        this.client = client
        this.visitToTheClient = visitToTheClient
        this.experience = experience
        this.specialization = specialization
        this.services = services
        this.visitReminder = visitReminder
        this.schedule = schedule
        this.breaks = breaks
    }

}
