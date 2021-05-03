package com.nick_sib.beauty_radar.model.provider.calendar

class RemoteDBProviderCalendar: IRemoteDBProviderCalendar {

    override fun getListCalendarProfile(): List<CalendarProfile> {
        //Test function!!!!
        //Mock imitation loading users for MasterClientScreen
        return listOf(
            CalendarProfile("fkglknglksnglknf", "Саша", "1", "массаж"),
            CalendarProfile("fkglknglksngsadsadlknf", "Vasya", "4", "шугаринг"),
            CalendarProfile("fkglknglksngsadaflknf", "Olya", "4", "увеличение чего то"),
            CalendarProfile("fkglknglksnglknf", "Petya", "1", "уменьшение чего то"),
            CalendarProfile("fkglkngldsadlknf", "Ira", "4", "ресницы"),
            CalendarProfile("fkglksadaflknf", "Lesha", "4", "губы")
        )
    }
}