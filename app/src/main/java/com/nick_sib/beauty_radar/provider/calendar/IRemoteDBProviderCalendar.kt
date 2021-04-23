package com.nick_sib.beauty_radar.provider.calendar

import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.calendar.entities.CalendarProfile

interface IRemoteDBProviderCalendar {

    fun test_getListData():List<CalendarProfile>

    fun createCalendarDateInDb(calendar: CalendarProfile)
    suspend fun getCalendarDateFromDb(uid: String) : AppState
}