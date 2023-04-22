package com.example.habit2.enums

enum class WeekDay(val title: String, val full: String, val num: Int) {
    SUN("S", "Sunday", 0),
    MON("M", "Monday", 1),
    TUES("T", "Tuesday", 2),
    WED("W", "Wednesday", 3),
    THURS("T", "Thursday", 4),
    FRI("F", "Friday", 5),
    SAT("S", "Saturday", 6);

    companion object {

        fun getDay(num: Int): WeekDay {
            return when(num) {
                0 -> SUN
                1 -> MON
                2 -> TUES
                3 -> WED
                4 -> THURS
                5 -> FRI
                6 -> SAT
                else -> MON
            }
        }
    }
}