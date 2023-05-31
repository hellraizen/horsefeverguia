package com.dleite.horsefeverguia.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dleite.horsefeverguia.R
import com.dleite.horsefeverguia.databinding.FragmentListCardBinding
import com.dleite.horsefeverguia.ui.adapter.CardAdapter
import com.dleite.horsefeverguia.ui.core.viewBinding
import com.dleite.horsefeverguia.ui.models.CardHorse
import com.dleite.horsefeverguia.ui.viewmodel.CardListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardListFragment : BaseFragment(R.layout.fragment_list_card) {

    private val binding by viewBinding(FragmentListCardBinding::bind)

    private val viewModel: CardListViewModel by viewModel()

    private val controlador by lazy {
        findNavController()
    }


    override fun baseInitEvents() {
        super.baseInitEvents()
        setupListeners()
    }

    override fun baseInitObservers() {
        super.baseInitObservers()
        viewModel.cardsData.observe(viewLifecycleOwner) { cards ->
            setRecyclerView(cards)
        }
    }

    private fun setupListeners() {
        viewModel.getCards()
    }

    private fun setRecyclerView(cards: List<CardHorse>) {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CardAdapter(cards, onItemClickListener = {
                goDetails()
            })
            isClickable = true

        }
    }

    private fun goDetails() {
        controlador.navigate(
            CardListFragmentDirections
                .actionCardListToCardDetailsFragment()
        )
    }

}