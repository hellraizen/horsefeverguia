package com.dleite.horsefeverguia.data.repository.firebase

import com.dleite.horsefeverguia.domain.models.CardHorseModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val COLECAO_FIRESTORE_CARDS = "cards"

class CardRepositoryImpl(
    private val firestore: FirebaseFirestore
) : CardRepository {
    override suspend fun getCards(): List<CardHorseModel> {
        var cards: List<CardHorseModel> = emptyList()
        return suspendCoroutine { continuation ->
            try {
                var control = true
                firestore.collection(COLECAO_FIRESTORE_CARDS)
                    .orderBy("title")
                    .addSnapshotListener { s, _ ->
                        s?.let { snapshot ->
                            cards = snapshot.documents
                                .mapNotNull { card ->
                                    coverToCardHouseModel(card)
                                }
                        }
                        if (control){
                            continuation.resume(cards)
                            control = false
                        }
                    }
            } catch (e: Exception) {
                continuation.resumeWithException(e)

            }
        }
    }


    override suspend fun getCardId(id: String): CardHorseModel {
        var card = CardHorseModel()
        return suspendCoroutine { continuation ->
            try {
                var control = true
                firestore.collection(COLECAO_FIRESTORE_CARDS)
                    .document(id)
                    .addSnapshotListener { s, _ ->
                        s?.let { cardDocument ->
                            coverToCardHouseModel(cardDocument).let {
                                card = it!!
                            }
                        }
                        if (control){
                            continuation.resume(card)
                            control = false
                        }
                    }
            } catch (e: Exception) {
                continuation.resumeWithException(e)

            }

        }
    }

    private fun coverToCardHouseModel(document: DocumentSnapshot): CardHorseModel? =
        document.toObject<CardHoseDocumento>()?.paraCardHorseModel(document.id)

    private class CardHoseDocumento(
        val title: String = "",
        val description: String = "",
        val img: String = "",
        val category: String = ""
    ) {
        fun paraCardHorseModel(id: String): CardHorseModel = CardHorseModel(
            id = id,
            title = title,
            description = description,
            img = img,
            category = category
        )
    }
}