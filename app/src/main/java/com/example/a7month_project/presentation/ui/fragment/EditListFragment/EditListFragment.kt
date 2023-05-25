package com.example.a7month_project.presentation.ui.fragment.EditListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.a7month_project.R
import com.example.a7month_project.databinding.FragmentEditListBinding
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.presentation.ui.fragment.listofnote.ListOfNoteViewModel
import com.example.a7month_project.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditListFragment : Fragment() {
    private lateinit var binding: FragmentEditListBinding
    private val viewModel by viewModels<EditListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiListener()
    }

    private fun intiListener() {
        binding.btnSend.setOnClickListener {
            createNote()
        }
    }

    private fun editNote(note: Note) {
        viewModel.editNotes(note)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editNotesState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                            //show progress bar
                        }
                        is UIState.Success -> {
                            //set list  to adapter
                        }
                    }
                }
            }
        }
    }

    private fun createNote() {
        viewModel.createNotes(Note(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        ))




        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.createNotesState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                            //show progress bar
                        }
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                    }
                }
            }

        }
    }


}