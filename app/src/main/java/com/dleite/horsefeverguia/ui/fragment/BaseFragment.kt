package com.dleite.horsefeverguia.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment(fragment: Int) : Fragment(fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseInitEvents()
        baseInitObservers()
    }

    open fun baseInitObservers() {
    }

    open fun baseInitEvents() {
    }
}