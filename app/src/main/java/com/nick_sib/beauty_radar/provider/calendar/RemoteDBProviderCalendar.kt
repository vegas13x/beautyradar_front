package com.nick_sib.beauty_radar.provider.calendar

import com.google.firebase.database.*
import com.nick_sib.beauty_radar.data.error.ToastError
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.calendar.entities.CalendarProfile
import com.nick_sib.beauty_radar.ui.utils.CALENDAR_DATE_IS_DISABLE_IN_DB
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteDBProviderCalendar : IRemoteDBProviderCalendar{

    private lateinit var databaseСalendar: DatabaseReference

    override fun createCalendarDateInDb(calendar: CalendarProfile) {
        databaseСalendar =
            calendar.uid?.let {
                FirebaseDatabase.getInstance().getReference("CALENDAR_PROFILE").child(it)
            }!!
        databaseСalendar.setValue(calendar)
    }

    override suspend fun getCalendarDateFromDb(uid: String) : AppState {
        return suspendCoroutine { res ->
            databaseСalendar =
                FirebaseDatabase.getInstance().getReference("CALENDAR_PROFILE").child(uid)
            databaseСalendar.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == null) {
                        res.resume(AppState.Success(CALENDAR_DATE_IS_DISABLE_IN_DB))
                    } else {
                        var hashMap = snapshot.value as HashMap<String, String>
                        var calendarDate = CalendarProfile(hashMap["uid"],hashMap["name"],hashMap["dateStart"],hashMap["dateEnd"])
                        res.resume(AppState.Success(calendarDate))
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    res.resume(AppState.Error(ToastError(error.message)))
                }
            })
        }
    }
}