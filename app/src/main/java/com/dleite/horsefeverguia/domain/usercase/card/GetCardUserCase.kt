package com.dleite.horsefeverguia.domain.usercase.card

import com.dleite.horsefeverguia.ui.models.CardHorse

interface GetCardUserCase {
    suspend operator fun invoke() : List<CardHorse>
}

