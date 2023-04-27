package com.example.habit2.enums

enum class WeekDay(val title: String) {
    SUN("S"),
    MON("M"),
    TUES("T"),
    WED("W"),
    THURS("T"),
    FRI("F"),
    SAT("S", );

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