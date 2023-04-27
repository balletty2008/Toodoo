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