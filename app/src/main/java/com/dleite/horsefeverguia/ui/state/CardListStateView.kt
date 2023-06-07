package com.dleite.horsefeverguia.ui.state

import com.dleite.horsefeverguia.ui.models.CardHorse

data class CardListStateView(
    val isLoading: Boolean = true,
    val cardList: List<CardHorse> = emptyList(),
    val errorMessage: String = "",
)