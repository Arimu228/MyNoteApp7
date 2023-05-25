package com.example.a7month_project.presentation.Base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.a7month_project.presentation.utils.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    protected fun <T> StateFlow<UIState<T>>.collectState(
        state: (UIState<T>) -> Unit,
        onSuccess: (data: T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                this@collectState.collect { result ->
                    state.invoke(result)
                    when (result) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                        }
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            onSuccess(result.data)
                        }
                    }
                }
            }

        }
    }

}