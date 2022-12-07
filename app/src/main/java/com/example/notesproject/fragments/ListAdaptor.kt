package com.example.notesproject.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesproject.R
import com.example.notesproject.data.Note
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdaptor: RecyclerView.Adapter<ListAdaptor.ViewHolder>() {
    private var noteList = emptyList<Note>()

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.textId.text = currentItem.id.toString()
        holder.itemView.textName.text = currentItem.name.toString()
        holder.itemView.textDesc.text = currentItem.description.toString()
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(notes : List<Note>) {
        this.noteList = notes
        notifyDataSetChanged()
    }
}