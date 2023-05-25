package com.example.a7month_project.presentation.ui.fragment.listofnote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7month_project.databinding.ItemListOfNoteBinding
import com.example.a7month_project.domain.model.Note


class ListOfNoteAdapter(val onItemClick: (Note) -> Unit) :
    RecyclerView.Adapter<ListOfNoteAdapter.ListNoteViewHolder>() {
    private var items = arrayListOf<Note>()

    inner class ListNoteViewHolder(private val binding: ItemListOfNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Note) {

            binding.titleText.text = item.title
            binding.descText.text = item.desc

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    fun setList(liste: List<Note>) {
        items = liste as ArrayList<Note>
    }

    fun deleteItemsAndNotifyAdapter(pos: Note) {
        items.remove(pos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNoteViewHolder {
        val binding = ItemListOfNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListNoteViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size
}