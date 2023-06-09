package com.dleite.horsefeverguia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dleite.horsefeverguia.domain.usercase.card.GetCardIdUserCase
import com.dleite.horsefeverguia.ui.models.CardHorse
import com.dleite.horsefeverguia.ui.state.CardDetailsStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardDetailsViewModel(
    private val cardId: String,
    private val getCardIdUserCase: GetCardIdUserCase
) : ViewModel() {

    private val _states = MutableStateFlow(CardDetailsStateView(isLoading = true))
    val states: StateFlow<CardDetailsStateView> = _states

    init {
        getCard(cardId)
    }

    fun getCard(id: String) {
        viewModelScope.launch {
            runCatching {
                getCardIdUserCase(id)
            }.onSuccess {
                _states.value = _states.value.copy(isLoading = false, card = it)
            }.onFailure {
                _states.value = _states.value.copy(
                    isLoading = false,
                    card = CardHorse(),
                    errorMessage = "Algo deu errado"
                )
            }
        }
    }


}