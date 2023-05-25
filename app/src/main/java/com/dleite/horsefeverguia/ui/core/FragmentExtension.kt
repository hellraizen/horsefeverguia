package com.dleite.horsefeverguia.ui.core

import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

val Fragment.navController: NavController
    get() = NavHostFragment.findNavController(this)

// fun BaseFragment.addOnBackPressed(
//     enabled: Boolean = true,
//     onBackpressed: () -> Unit
// ): OnBackPressedCallback {
//     return object : OnBackPressedCallback(enabled) {
//         override fun handleOnBackPressed() {
//             onBackpressed.invoke()
//         }
//     }.apply {
//         requireActivity().onBackPressendDispatcher.addCallback(requireViewLifecycleOwner(), this)
//     }
// }

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
): ReadOnlyProperty<Fragment, T> = createViewDataBindingReadOnlyProperty(viewBindingFactory)

fun <T : ViewBinding> DialogFragment.viewBinding(
    viewBindingFactory: (View) -> T
): ReadOnlyProperty<Fragment, T> = createViewDataBindingReadOnlyProperty(viewBindingFactory)

private inline fun <reified F : Fragment, T> F.createViewDataBindingReadOnlyProperty(
    crossinline viewBindingFactory: (View) -> T
): ReadOnlyProperty<F, T> =
    object : ReadOnlyProperty<F, T> {
        private var binding: T? = null

        init {
            viewLifecycleOwnerLiveData.observe(
                this@createViewDataBindingReadOnlyProperty,
                Observer { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            (binding as? ViewDataBinding)?.unbind()
                            binding = null
                        }
                    })
                })
            lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onStart(owner: LifecycleOwner) {
                    view
                        ?: error("You must pass in the layout ID into ${this@createViewDataBindingReadOnlyProperty.javaClass.simpleName}'s constructor or inflate a view in onCreateView()")
                }
            })
        }

        override fun getValue(thisRef: F, property: KProperty<*>): T {
            binding?.let { return it }
            val viewLifecycleOwner = try {
                thisRef.viewLifecycleOwner
            } catch (e: java.lang.IllegalStateException) {
                error("the fragment has not called onCreateView() at this point")
            }
            if (!viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                error("The fragment has already called onDestroyView()")
            }
            return viewBindingFactory(thisRef.requireView()).also { viewBinding ->
                  if (viewBinding is ViewDataBinding) {
                      viewBinding.lifecycleOwner = viewLifecycleOwner
                  }
                this.binding = viewBinding

            }

        }

    }

fun NavController.isOnBackStack(@IdRes id: Int): Boolean = try {
    getBackStackEntry(id); true
} catch (e: Throwable) {
    false
}
