package com.example.notesproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesproject.R
import com.example.notesproject.model.Note
import com.example.notesproject.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_add_note.view.*

class AddNote : Fragment() {
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        view.button.setOnClickListener{
            insertNoteToDatabase()
        }
        return view
    }

    private fun insertNoteToDatabase(){
        val name = addName_et.text.toString()
        val desc = addDescription_et.text.toString()
        val date = addDate_et.text.toString()
        if(inputCheck(name, desc, date))
        {
            val note = Note(0, name, desc, date)
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Successfull", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addNote_to_listNotes)
        }
        else
        {
            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String, desc: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && TextUtils.isEmpty(date))
    }

}