package com.example.hugo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(val text: String, val points: Int, val canInputExtraData: Boolean = false) : Parcelable

@Parcelize
data class Question(val text: String, val options: List<Option>) : Parcelable {
    companion object {
        val questions = listOf(
            Question(
                "Kde si to cital?",
                listOf(
                    Option("Internet", 1),
                    Option("Facebook", 2),
                    Option("Ina socialna", 3),
                    Option("Casopis alebo noviny", 3),
                    Option("Kniha", 0)
                )
            ),
            Question(
                "Poznas meno autora?",
                listOf(
                    Option("Ano", 0),
                    Option("Nie", 1),
                )
            )
        )
    }
}