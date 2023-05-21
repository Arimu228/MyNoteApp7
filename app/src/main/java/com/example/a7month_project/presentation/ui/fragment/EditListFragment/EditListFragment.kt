package com.example.a7month_project.presentation.ui.fragment.EditListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a7month_project.R
import com.example.a7month_project.databinding.FragmentEditListBinding

class EditListFragment : Fragment() {
    private lateinit var binding: FragmentEditListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditListBinding.inflate(inflater, container, false)
        return binding.root
        intiListener()
    }

    private fun intiListener() {
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_listOfNoteFragment_to_editListFragment)
        }
    }


}