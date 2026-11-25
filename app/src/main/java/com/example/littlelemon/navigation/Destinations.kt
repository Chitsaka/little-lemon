package com.example.littlelemon.navigation

interface Destinations {
    val route: String
    companion object {
        val Home = object: Destinations {
            override val route = "home"
        }
        val Onboarding = object: Destinations {
            override val route = "onboarding"
        }
        val Profile = object: Destinations {
            override val route = "profile"
        }
    }
}