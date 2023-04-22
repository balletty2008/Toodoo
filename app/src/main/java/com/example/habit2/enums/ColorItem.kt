package com.example.habit2.enums

enum class ColorItem(var id: Int, var num: Long, var check: Boolean) {
    Color0(0,0xFF7E57C2,false),
    Color1(1,0xFF5C6BC0,false),
    Color2(2,0xFF42A5F5,false),
    Color3(3,0xFF26C6DA,false);


    companion object {

        fun getColor(num: Int): ColorItem {
            return when(num) {
                0 -> Color0
                1 -> Color1
                2 -> Color2
                else -> Color3

            }
        }
    }
}