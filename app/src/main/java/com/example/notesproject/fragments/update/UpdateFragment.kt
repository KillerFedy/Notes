package com.example.notesproject.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesproject.R
import com.example.notesproject.model.Note
import com.example.notesproject.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.updateName_et.setText(args.currentNote.name)
        view.updateDescription_et.setText(args.currentNote.description)
        view.updateDate_et.setText(args.currentNote.date)
        view.updateButton.setOnClickListener {
            UpdateItem()
        }

        setHasOptionsMenu(true)

        return view
    }
    private fun UpdateItem(){
        val name = updateName_et.text.toString()
        val desc = updateDescription_et.text.toString()
        val date = updateDate_et.text.toString()

        if(inputCheck(name, desc, date)) {
            val updateNote = Note(args.currentNote.id, name, desc, date)

            mNoteViewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listNotes)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, desc: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(desc) && TextUtils.isEmpty(date))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(),
            "Successfully removed ${args.currentNote.name}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listNotes)
        }
        builder.setNegativeButton("No") {_, _ ->
        }
        builder.setTitle("Delete ${args.currentNote.name}")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.name}?")
        builder.create().show()
    }

}