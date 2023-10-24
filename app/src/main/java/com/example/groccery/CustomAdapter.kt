package com.example.groccery

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast

class CustomAdapter(context: Context, resource: Int, items: List<String>) :
    ArrayAdapter<String>(context, resource, items) {

    private val selectedItems = HashSet<Int>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val checkBox = view.findViewById<CheckBox>(R.id.testbutton)
        checkBox.isChecked = selectedItems.contains(position)

        checkBox.setOnClickListener {
            if (checkBox.isChecked) {
                selectedItems.add(position)
                // Handle your logic for checked items here
                Toast.makeText(
                    context, "Selected", Toast.LENGTH_SHORT
                ).show()
            } else {
                selectedItems.remove(position)
                // Handle your logic for unchecked items here
            }
        }

        return view
    }
}
