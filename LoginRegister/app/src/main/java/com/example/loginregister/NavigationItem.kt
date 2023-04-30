package com.example.loginregister

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Home : NavigationItem("home", R.drawable.baseline_home_24, "Home")
    object Profile : NavigationItem("profile", R.drawable.baseline_person_24, "Profile")
    //object Settings : NavigationItem("settings", R.drawable.baseline_settings_24, "Settings")
    //object Share : NavigationItem("share", R.drawable.baseline_share_24, "Share")
    //object Contact : NavigationItem("contact", R.drawable.baseline_contact_phone_24, "Contact")

    object Calendar : NavigationItem("calendar", R.drawable.baseline_calendar_month_24, "Calendar")     //where it called
    object Tracker : NavigationItem("tracker", R.drawable.baseline_ssid_chart_24, "Timeline")
    object Login : NavigationItem("login", R.drawable.baseline_login_24, "Login")
    object Todos : NavigationItem("todos", R.drawable.baseline_playlist_add_check_24, "Todos")
}

