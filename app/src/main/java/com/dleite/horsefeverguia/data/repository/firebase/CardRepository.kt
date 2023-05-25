package com.dleite.horsefeverguia.data.repository.firebase

import com.dleite.horsefeverguia.domain.models.CardHorseModel

interface CardRepository {

    suspend fun getCards(): List<CardHorseModel>

}