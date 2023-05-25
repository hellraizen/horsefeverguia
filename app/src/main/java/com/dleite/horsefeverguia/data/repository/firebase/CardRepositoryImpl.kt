package com.dleite.horsefeverguia.data.repository.firebase

import com.dleite.horsefeverguia.domain.models.CardHorseModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.suspendCoroutine

private const val COLECAO_FIRESTORE_PRODUTOS = "cards"

class CardRepositoryImpl(
    private val firestore: FirebaseFirestore
) : CardRepository {
    override suspend fun getCards(): List<CardHorseModel> {
        firestore.collection(COLECAO_FIRESTORE_PRODUTOS)
        return suspendCoroutine { continuation ->
            val cardsReference = firestore.collection(COLECAO_FIRESTORE_PRODUTOS)
            cardsReference.get().addOnSuccessListener { documents ->
                val cards = mutableListOf<CardHorseModel>()
                for (document in documents) {
                    document.toObject(CardHorseModel::class.java).run {
                        cards.add(this)
                    }
                }

                continuation.resumeWith(Result.success(cards))
            }

            cardsReference.get().addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }
}