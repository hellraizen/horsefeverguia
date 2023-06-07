package com.dleite.horsefeverguia.domain.usercase.card

import com.dleite.horsefeverguia.data.repository.firebase.CardRepository
import com.dleite.horsefeverguia.domain.usercase.extensions.toCardHorse
import com.dleite.horsefeverguia.ui.models.CardHorse

class GetCardUserCaseImpl(
    private val cardRepository: CardRepository
) : GetCardUserCase {

    override suspend fun invoke(): List<CardHorse> {
        return cardRepository.getCards().toCardHorse()
    }
}

class GetCardIdUserCaseImpl(
    private val cardRepository: CardRepository
) : GetCardIdUserCase {

    override suspend fun invoke(id: String): CardHorse {
        return cardRepository.getCardId(id).toCardHorse()
    }
}

