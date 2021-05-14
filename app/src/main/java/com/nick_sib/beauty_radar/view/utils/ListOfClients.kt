package com.nick_sib.beauty_radar.view.utils

import com.nick_sib.beauty_radar.model.provider.calendar.CalendarProfile

class ListOfClients {

    fun getClients(): List<CalendarProfile> {
        var listClients = mutableListOf<CalendarProfile>()
        var calendarProfile = CalendarProfile()
        calendarProfile.name="Катя"
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        listClients.add(calendarProfile)
        return  listClients
    }

}