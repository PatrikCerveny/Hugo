package com.example.hugo

data class Answer(val text: String, val points: Int, val extraData: String? = null)
data class Question(val text: String, val answers: List<Answer>) {
    companion object {
        val questions = listOf(
            Question(
                "Kde si to cital?",
                listOf(
                    Answer("Internet", 1),
                    Answer("Facebook", 2),
                    Answer("Ina socialna", 3),
                    Answer("Casopis alebo noviny", 3),
                    Answer("Kniha", 0)
                )
            ),
            Question(
                "Poznas meno autora?",
                listOf(
                    Answer("Ano", 0),
                    Answer("Nie", 1),
                )
            )
        )
    }
}