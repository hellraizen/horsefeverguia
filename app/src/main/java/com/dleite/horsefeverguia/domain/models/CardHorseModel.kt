package com.dleite.horsefeverguia.domain.models

import com.google.firebase.firestore.PropertyName


data class CardHorseModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    @get:PropertyName("img")
    @set:PropertyName("img")
    var img: String = ""
)
