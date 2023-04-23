package com.example.habit2.enums

enum class Status(status: Boolean) {
    SUN(false),
    MON(false),
    TUES(false),
    WED(false),
    THURS(false),
    FRI(false),
    SAT(false);

    companion object {

        fun getStatus(num: Int): Enum<*> {
            return when(num) {

                1 -> Status.MON
                2 -> Status.TUES
                3 -> Status.WED
                4 -> Status.THURS
                5 -> Status.FRI
                6 -> Status.SAT
                else -> Status.SUN
            }
        }
    }
}