package com.example.groccery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModal>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModal("Молочные продукты", R.drawable.milk)
        courseList = courseList + GridViewModal("ВАРКА И ВЫПЕЧКА", R.drawable.milk)
        courseList = courseList + GridViewModal("СОУСЫ И ДОБАВКИ", R.drawable.milk)
        courseList = courseList + GridViewModal("СЛАДКОЕ, НАПИТКИ И ЗАКУСКИ", R.drawable.milk)
        courseList = courseList + GridViewModal("ЗАМОРОЖЕННЫЕ", R.drawable.milk)
        courseList = courseList + GridViewModal("ОВОЩИ", R.drawable.milk)
        courseList = courseList + GridViewModal("ФРУКТЫ", R.drawable.milk)
        courseList = courseList + GridViewModal("УБОРКА И ОБСЛУЖИВАНИЕ ДОМА", R.drawable.milk)
        courseList = courseList + GridViewModal("ФРУКТЫ", R.drawable.milk)
        courseList = courseList + GridViewModal("УБОРКА И ОБСЛУЖИВАНИЕ ДОМА", R.drawable.milk)


        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = GridRVAdapter(courseList = courseList, this@MainActivity)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                applicationContext, courseList[position].courseName + " selected",
                Toast.LENGTH_SHORT

            ).show()
            if(view.alpha!=0.5f)
                view.alpha=0.5f
            else
                view.alpha=1.0f

            val fab: View = findViewById(R.id.fab)
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }

//        // use arrayadapter and define an array
//        val arrayAdapter: ArrayAdapter<*>
//        val users = arrayOf(
//            "Молочные продукты", "ВАРКА И ВЫПЕЧКА", "СОУСЫ И ДОБАВКИ", "СЛАДКОЕ, НАПИТКИ И ЗАКУСКИ", "ЗАМОРОЖЕННЫЕ", "мясо и рыба",
//            "овощи", "фрукты", "УБОРКА И ОБСЛУЖИВАНИЕ ДОМА"
//        )
//
//        // access the listView from xml file
//        var mListView = findViewById<GridView>(R.id.list_view)
//        arrayAdapter = ArrayAdapter(this,
//            android.R.layout.simple_list_item_1, users)
//        mListView.adapter = arrayAdapter
        }
    }
}
