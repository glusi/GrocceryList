package com.example.groccery

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.io.IOException
import java.io.Serializable
import android.content.ContentValues

import android.provider.MediaStore
import android.view.ViewTreeObserver

import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

import java.io.OutputStream

class ListGen : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var selected_lines: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_gen)

        selected_lines = ArrayList<String>()

        var bundle: Bundle? = intent.extras
        val args = intent.getBundleExtra("BUNDLE")
        val selectedPages = args!!.getSerializable("selected") as ArrayList<Int>
        var position = bundle!!.getInt("position")

        var titles: ArrayList<String> = MainActivity.titles
        val title: TextView = findViewById(R.id.title)

        var lists: ArrayList<List<String>> = MainActivity.lists
        listView = findViewById(R.id.list_products)

        var output: ArrayList<String> = MainActivity.output

        if (position == selectedPages.size) {
            title.text = "Список покупок готов!"
            listView.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, output)

        } else {
            val pageNumber = selectedPages.get(position)
            title.text = titles[pageNumber]


            listView.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, lists[pageNumber])

            listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            listView.onItemClickListener =
                AdapterView.OnItemClickListener { _, view_item, position_iternal, _ ->
                    Toast.makeText(
                        applicationContext, " selected",
                        Toast.LENGTH_SHORT
                    ).show()
                    if (view_item.alpha != 0.5f) {
                        view_item.alpha = 0.5f
                        selected_lines.add(listView.getItemAtPosition(position_iternal).toString())
                    } else {
                        view_item.alpha = 1.0f
                        selected_lines.remove(
                            listView.getItemAtPosition(position_iternal).toString()
                        )
                    }
                }

            position += 1
            val fab: View = findViewById(R.id.next)
            fab.setOnClickListener { view ->
                output.addAll(selected_lines)
                val intent = Intent(this@ListGen, ListGen::class.java)
                intent.putExtra("position", position)
                val args = Bundle()
                args.putSerializable("selected", selectedPages as Serializable?)
                intent.putExtra("BUNDLE", args)

                startActivity(intent)
            }
        }
    }


}
