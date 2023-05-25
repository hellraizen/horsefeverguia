package com.dleite.horsefeverguia.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.dleite.horsefeverguia.R
import com.dleite.horsefeverguia.databinding.FragmentListCardBinding
import com.dleite.horsefeverguia.ui.adapter.CardAdapter
import com.dleite.horsefeverguia.ui.core.viewBinding
import com.dleite.horsefeverguia.ui.viewmodel.CardListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardListFragment : BaseFragment(R.layout.fragment_list_card) {

    private val binding by viewBinding (FragmentListCardBinding::bind)

    private val viewModel: CardListViewModel by viewModel()

    private val cardAdapter = CardAdapter()

    override fun baseInitEvents() {
        super.baseInitEvents()
        setRecyclerView()
        setupListeners()
    }

    override fun baseInitObservers() {
        super.baseInitObservers()
        viewModel.cardsData.observe(viewLifecycleOwner) { cards ->
            cardAdapter.submitList(cards)
        }
    }
    private fun setupListeners(){
        viewModel.getCards()
    }

    private fun setRecyclerView() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardAdapter
        }
    }

}