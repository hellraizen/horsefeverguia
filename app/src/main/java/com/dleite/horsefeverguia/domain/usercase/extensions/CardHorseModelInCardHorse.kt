package com.dleite.horsefeverguia.domain.usercase.extensions

import com.dleite.horsefeverguia.domain.models.CardHorseModel
import com.dleite.horsefeverguia.ui.models.CardHorse

fun List<CardHorseModel>.toCardHorse(): List<CardHorse> =
    this.map {
        CardHorse(
            img = it.img,
            title = it.title,
            description = it.description
        )
    }