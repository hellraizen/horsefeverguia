package com.dleite.horsefeverguia.ui.fragment

import com.dleite.horsefeverguia.R
import com.dleite.horsefeverguia.databinding.FragmentDetailsCardBinding
import com.dleite.horsefeverguia.ui.adapter.CardAdapter
import com.dleite.horsefeverguia.ui.core.viewBinding
import com.dleite.horsefeverguia.ui.viewmodel.CardDatailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsFragment : BaseFragment(R.layout.fragment_details_card) {

    private val binding by viewBinding (FragmentDetailsCardBinding::bind)

    private val viewModel: CardDatailsViewModel by viewModel()


    override fun baseInitEvents() {
        super.baseInitEvents()
        setupListeners()
    }

    override fun baseInitObservers() {
        super.baseInitObservers()
     //   viewModel.cardsData.observe(viewLifecycleOwner) { cards ->
     //       cardAdapter.submitList(cards)
     //   }
    }
    private fun setupListeners(){
       // viewModel.getCards()
    }



}