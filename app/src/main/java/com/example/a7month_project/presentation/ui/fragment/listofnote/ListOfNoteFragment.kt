package com.example.a7month_project.presentation.ui.fragment.listofnote


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.a7month_project.R
import com.example.a7month_project.databinding.FragmentListOfNoteBinding
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.presentation.Base.BaseFragment
import com.example.a7month_project.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfNoteFragment : BaseFragment() {
    private lateinit var binding: FragmentListOfNoteBinding
    private val viewModel by viewModels<ListOfNoteViewModel>()
    private lateinit var adapter: ListOfNoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initListener() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_listOfNoteFragment_to_editListFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ListOfNoteAdapter(this::onItemClick)
    }

    private fun onItemClick(note: Note) {
        adapter.deleteItemsAndNotifyAdapter(note)
        removeNotes(note)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        getAllNotes()

    }

    private fun getAllNotes() {
        viewModel.getAllNotes()
        viewModel.getAllNotesState.collectState(
            state = { state ->
                binding.progressBar.isVisible = state is UIState.Loading

            }, onSuccess = { data ->
                binding.recyclerList.adapter = adapter
                adapter.setList(data)

            })
    }
//    private fun updateData(note: Note) {
//        note.title = binding. .text.toString()
//
//        taskModel.description = binding.etDesc.text.toString()
//        App.db.Dao().updateTask(taskModel)// и здесь ее обновиить
//    }

    private fun removeNotes(note: Note) {
        viewModel.removeNotes(note)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.removeAllNotesState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        is UIState.Success -> {
                            Toast.makeText(requireContext(), "Success deleted", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }

}