package com.dleite.horsefeverguia.ui.models

import android.content.Context
import android.view.ContextThemeWrapper
import com.google.android.material.chip.Chip

class FilterItem(
    val id: Int,
    val text: String
) {
    companion object {
        fun getListFilter() = arrayOf(
            FilterItem(1, "Todos"),
            FilterItem(2, "Cavalo"),
            FilterItem(3, "Ação"),
            FilterItem(4, "Assistente"),
        )
    }
}

fun FilterItem.toChip(context: Context): Chip {
    val chip = Chip(
        ContextThemeWrapper(
            context,
            com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice
        )
    )
    chip.setOnClickListener { view ->
        val text = (view as Chip).text.toString()
    }
    chip.text = text
    return chip
}
