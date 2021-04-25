package com.nick_sib.beauty_radar.model.provider.calendar

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.entities.CalendarProfile

interface IRemoteDBProviderCalendar {

    fun getListCalendarProfile():List<CalendarProfile>

    fun createCalendarDateInDb(calendar: CalendarProfile)
    suspend fun getCalendarDateFromDb(uid: String) : AppState
}