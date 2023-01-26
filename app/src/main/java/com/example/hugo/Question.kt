package com.example.hugo

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(val text: String, val points: Int, val canInputExtraData: Boolean = false) : Parcelable

@Parcelize
data class Question(val text: String, val options: List<Option>, val image: Int) : Parcelable {
    companion object {
        val questions = listOf(
            Question(
                "Poznáš meno autora?",
                listOf(
                    Option("Áno", 0),
                    Option("Nie", 1),
                ),
               R.drawable.author
            ),
            Question(
                "Čítal si to aj inde? Máš viac zrojov?",
                listOf(
                    Option("Áno", 0),
                    Option("Nie", 1),
                ),
                R.drawable.sources
            ),

            Question(
                "Spája sa s informáciou aj predaj produktov?",
                listOf(
                    Option("Áno", 1),
                    Option("Nie", 0),
                ),
                R.drawable.products
            ),

            Question(
                "Vyvoláva táto informácia v tebe strach?",
                listOf(
                    Option("Áno", 1),
                    Option("Nie", 0),
                ),
                R.drawable.fear
            ),

            Question(
                "Vymyká sa informácia bežnému poznanu, resp. spoločenskému konsenzu?",
                listOf(
                    Option("Áno", 1),
                    Option("Nie", 0),
                ),
                R.drawable.consensus
            ),

            Question(
                "Je to šokujúca správa?",
                listOf(
                    Option("Áno", 1),
                    Option("Nie", 0),
                ),
                R.drawable.shocked
            ),

            Question(
                "Rozdeľuje táto informácia spoločnosť?",
                listOf(
                    Option("Áno", 1),
                    Option("Nie", 0),
                ),
                R.drawable.divisions
            ),
            Question(
                "Kde si to čítal?",
                listOf(
                    Option("Internet", 2),
                    Option("Facebook", 5),
                    Option("Ina sociálna sieť", 4),
                    Option("Televízia alebo rádio", 3),
                    Option("Časopis alebo noviny", 3),
                    Option("Kniha", 1)
                ),
                R.drawable.source_of_information
            ),
        )
    }
}