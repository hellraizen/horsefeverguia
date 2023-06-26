package com.dleite.horsefeverguia.ui.fragment

import android.view.View
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dleite.horsefeverguia.R
import com.dleite.horsefeverguia.databinding.FragmentListCardBinding
import com.dleite.horsefeverguia.ui.adapter.CardAdapter
import com.dleite.horsefeverguia.ui.core.viewBinding
import com.dleite.horsefeverguia.ui.models.CardHorse
import com.dleite.horsefeverguia.ui.models.FilterItem
import com.dleite.horsefeverguia.ui.models.toChip
import com.dleite.horsefeverguia.ui.viewmodel.CardListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CardListFragment : BaseFragment(R.layout.fragment_list_card) {

    private val binding by viewBinding(FragmentListCardBinding::bind)

    private val viewModel: CardListViewModel by viewModel()

    private val filterItem = FilterItem.getListFilter()
    private lateinit var adapterLocation: CardAdapter
    private lateinit var cardListLocation: List<CardHorse>

    private val controlador by lazy {
        findNavController()
    }

    override fun baseInitEvents() {
        super.baseInitEvents()
        setupSearch()
        setFilter()
    }

    override fun baseInitObservers() {
        super.baseInitObservers()
        lifecycleScope.launch {
            viewModel.states.collect {
                setupLoading(it.isLoading)
                setRecyclerView(it.cardList)
                loadError(it.errorMessage)
            }
        }

    }

    private fun setFilter() {
        binding.let {
            filterItem.forEach { filter ->
                it.chipGroupFilter.addView(filter.toChip(requireContext()))
            }
        }
    }


    private fun setRecyclerView(cards: List<CardHorse>) {

        cardListLocation = cards
        adapterLocation = CardAdapter(cards, onItemClickListener = {
            goDetails(it.id)
        })
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterLocation
            isClickable = true
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }
            })
    }

    private fun filterList(name: String?) {
        if (name != null) {
            val filteredList = ArrayList<CardHorse>()
            for (i in cardListLocation) {
                if (i.title.lowercase(Locale.ROOT).contains(name)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                loadError("Não encontrado")
            } else {
                adapterLocation.setFilteredList(filteredList)
            }
        }
    }

    private fun goDetails(id: String) {
        controlador.navigate(
            CardListFragmentDirections
                .actionCardListToCardDetailsFragment(id)
        )
    }


    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

}