package com.nick_sib.beauty_radar.model.provider.calendar

class RemoteDBProviderCalendar: IRemoteDBProviderCalendar {

    override fun getListCalendarProfile(): List<CalendarProfile> {
        return listOf(
            CalendarProfile("fkglknglksnglknf", "Саша", "13:00", "массаж"),
            CalendarProfile("fkglknglksngsadsadlknf", "Vasya", "13:30", "шугаринг"),
            CalendarProfile("fkglknglksngsadaflknf", "Olya", "14:00", "увеличение чего то"),
            CalendarProfile("fkglknglksnglknf", "Petya", "16:00", "уменьшение чего то"),
            CalendarProfile("fkglkngldsadlknf", "Ira", "16:30", "ресницы"),
            CalendarProfile("fkglksadaflknf", "Lesha", "17:00", "губы")
        )
    }
}