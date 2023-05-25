package com.dleite.horsefeverguia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dleite.horsefeverguia.domain.usercase.card.GetCardUserCase
import com.dleite.horsefeverguia.ui.models.CardHorse
import kotlinx.coroutines.launch

class CardListViewModel(
    private val getCardUserCase: GetCardUserCase
) : ViewModel() {

    private val _cardsData = MutableLiveData<List<CardHorse>>()
    val cardsData: LiveData<List<CardHorse>> = _cardsData

    fun getCards() = viewModelScope.launch {
        try {
            val cards = getCardUserCase()
            _cardsData.value = cards

        } catch (e: Exception) {
            Log.d("userssViewModel", e.toString())
        }
    }

}