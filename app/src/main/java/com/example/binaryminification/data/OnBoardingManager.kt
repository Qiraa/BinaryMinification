package com.example.binaryminification.data

import android.content.Context
import androidx.core.content.edit

private const val ONBOARDING_ALREADY_SHOWN_KEY = "ONBOARDING_ALREADY_SHOWN_KEY"

class OnBoardingManager(context: Context) {

    private val onboardingPreferences = context
        .getSharedPreferences("ONBOARDING_PREFS", Context.MODE_PRIVATE)

    fun isOnBoardingShown(): Boolean {
        return onboardingPreferences.contains(ONBOARDING_ALREADY_SHOWN_KEY)
    }

    fun setOnBoardingShown() {
        onboardingPreferences.edit {
            putBoolean(ONBOARDING_ALREADY_SHOWN_KEY, true)
        }
    }
}