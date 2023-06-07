package com.dleite.horsefeverguia.data.repository.firebase

import com.dleite.horsefeverguia.domain.models.CardHorseModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlin.coroutines.suspendCoroutine

private const val COLECAO_FIRESTORE_PRODUTOS = "cards"

class CardRepositoryImpl(
    private val firestore: FirebaseFirestore
) : CardRepository {
    override suspend fun getCards(): List<CardHorseModel> {
        var cards: List<CardHorseModel> = emptyList()
        return suspendCoroutine { continuation ->
            firestore.collection(COLECAO_FIRESTORE_PRODUTOS)
                .addSnapshotListener() { s, _ ->
                    s?.let { snapshot ->
                        cards = snapshot.documents
                            .mapNotNull { card ->
                                converteParaProduto(card)
                            }
                    }

                    continuation.resumeWith(Result.success(cards))
                }
        }
    }


    override suspend fun getCardId(id: String): CardHorseModel {
        var card = CardHorseModel()
        return suspendCoroutine { continuation ->
            firestore.collection(COLECAO_FIRESTORE_PRODUTOS)
                .document(id)
                .addSnapshotListener { s, _ ->
                    s?.let { cardDocument ->
                        converteParaProduto(cardDocument).let {
                            card = it!!
                        }
                    }
                    continuation.resumeWith(Result.success(card))
                }
        }
    }

    private fun converteParaProduto(documento: DocumentSnapshot): CardHorseModel? =
        documento.toObject<ProdutoDocumento>()?.paraCardHorseModel(documento.id)

    private class ProdutoDocumento(
        val title: String = "",
        val description: String = "",
        val img: String = ""
    ) {
        fun paraCardHorseModel(id: String): CardHorseModel = CardHorseModel(
            id = id,
            title = title,
            description = description,
            img = img
        )
    }
}