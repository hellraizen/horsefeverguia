package com.dleite.horsefeverguia.ui.state

import com.dleite.horsefeverguia.ui.models.CardHorse

data class CardDetailsStateView(
    val isLoading: Boolean = true,
    val card: CardHorse = CardHorse(),
    val errorMessage: String = "",
)