package com.example.noteappviewasiri.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappviewasiri.MainActivity
import com.example.noteappviewasiri.databinding.NoteRowBinding
import com.example.noteappviewasiri.data.Note

class NoteAdapter(
    private val activity: MainActivity):
    RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {
    private var items= emptyList<Note>()

    class ItemViewHolder(val binding: NoteRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder
    {
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            tvNote.text = item.noteText
            if (position % 2 == 0) {
                llNoteHolder.setBackgroundColor(Color.rgb(213, 189, 218))
            } else {
                llNoteHolder.setBackgroundColor(Color.rgb(244, 218, 248))
            }
            ibEditNote.setOnClickListener {
                activity.raiseDialog(item.id)
            }
            ibDeleteNote.setOnClickListener {
                activity.mainViewModel.deleteNote(item.id)
            }
        }
    }



    override fun getItemCount() = items.size

    fun update(notes: List<Note>){
        println("UPDATING DATA")
        this.items = notes
        notifyDataSetChanged()
    }
}
