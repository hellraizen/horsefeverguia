package com.dleite.horsefeverguia.domain.usercase.extensions

import com.dleite.horsefeverguia.domain.models.CardHorseModel
import com.dleite.horsefeverguia.ui.models.CardHorse

fun List<CardHorseModel>.toCardHorse(): List<CardHorse> =
    this.map {
        CardHorse(
            id = it.id,
            img = it.img,
            title = it.title,
            description = it.description
        )
    }

fun CardHorseModel.toCardHorse(): CardHorse =
    CardHorse(
        id = this.id,
        img = this.img,
        title = this.title,
        description = this.description
    )
