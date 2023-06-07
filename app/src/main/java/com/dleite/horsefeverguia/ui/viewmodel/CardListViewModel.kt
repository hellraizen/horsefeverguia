package com.dleite.horsefeverguia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dleite.horsefeverguia.domain.usercase.card.GetCardUserCase
import com.dleite.horsefeverguia.ui.state.CardListStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardListViewModel(
    private val getCardUserCase: GetCardUserCase
) : ViewModel() {

    init {
        getCards()
    }

    private val _states = MutableStateFlow(CardListStateView(isLoading = true))
    val states: StateFlow<CardListStateView> = _states

    fun getCards() = viewModelScope.launch {
        runCatching {
            getCardUserCase()
        }.onSuccess {
            _states.value = _states.value.copy(isLoading = false, cardList = it)
        }.onFailure {
            _states.value = _states.value.copy(
                isLoading = false,
                cardList = emptyList(),
                errorMessage = "Algo deu errado"
            )
        }
    }

}