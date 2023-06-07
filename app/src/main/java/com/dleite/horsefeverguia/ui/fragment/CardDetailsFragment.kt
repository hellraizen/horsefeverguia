package com.dleite.horsefeverguia.ui.fragment

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.dleite.horsefeverguia.R
import com.dleite.horsefeverguia.databinding.FragmentDetailsCardBinding
import com.dleite.horsefeverguia.ui.core.viewBinding
import com.dleite.horsefeverguia.ui.models.CardHorse
import com.dleite.horsefeverguia.ui.viewmodel.CardDatailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CardDetailsFragment : BaseFragment(R.layout.fragment_details_card) {

    private val binding by viewBinding(FragmentDetailsCardBinding::bind)

    private val args: CardDetailsFragmentArgs by navArgs()
    private val cardId by lazy {
        args.cardId
    }

    private val viewModel: CardDatailsViewModel by viewModel { parametersOf(cardId) }


    override fun baseInitEvents() {
        super.baseInitEvents()
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun baseInitObservers() {
        super.baseInitObservers()
        lifecycleScope.launch {
            viewModel.states.collect {
                loadError(it.errorMessage)
                setupLoading(it.isLoading)
                loadCard(it.card)
            }
        }
    }

    private fun loadCard(card: CardHorse) {
        binding.run {
            title.text = card.title
            description.text = card.description
        }
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.loading.visibility = View.VISIBLE
        } else {
            binding.loading.visibility = View.GONE
        }
    }

}