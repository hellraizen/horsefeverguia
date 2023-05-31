package com.dleite.horsefeverguia.di

import com.dleite.horsefeverguia.data.repository.firebase.CardRepository
import com.dleite.horsefeverguia.data.repository.firebase.CardRepositoryImpl
import com.dleite.horsefeverguia.domain.usercase.card.GetCardUserCase
import com.dleite.horsefeverguia.domain.usercase.card.GetCardUserCaseImpl
import com.dleite.horsefeverguia.ui.adapter.CardAdapter
import com.dleite.horsefeverguia.ui.fragment.CardDetailsFragment
import com.dleite.horsefeverguia.ui.fragment.CardListFragment
import com.dleite.horsefeverguia.ui.viewmodel.CardListViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    single<CardRepository> { CardRepositoryImpl(get()) }

}

val uiModule = module {
    factory<CardListFragment> { CardListFragment() }
    factory<CardDetailsFragment> { CardDetailsFragment() }

}

val userCaseModule = module {
    factory<GetCardUserCase> {
        GetCardUserCaseImpl(get())
    }
}

val viewModelModule = module {
    viewModel<CardListViewModel>{ CardListViewModel(getCardUserCase = get()) }
}

val firebaseModule = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }
}