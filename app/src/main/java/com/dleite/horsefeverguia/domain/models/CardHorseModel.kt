package com.dleite.horsefeverguia.domain.models

import com.google.firebase.firestore.PropertyName


data class CardHorseModel(
    val id: String? = null,
    val title: String = "",
    val description: String = "",
    @get:PropertyName("img")
    @set:PropertyName("img")
    var img: String = ""
)
