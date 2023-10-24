package com.example.groccery
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable


class ListGen : AppCompatActivity() {
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_gen)

        var bundle :Bundle ?=intent.extras
        val args = intent.getBundleExtra("BUNDLE")
        val selected_pages = args!!.getSerializable("selected") as ArrayList<Int>?
        var position = bundle!!.getInt("position")
        print(position)

        var lists : ArrayList<List<String>> = MainActivity.lists
        listView = findViewById(R.id.list_products)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lists[position])

        position += 1
        val fab: View = findViewById(R.id.next)
        fab.setOnClickListener { view ->

            val intent = Intent(this@ListGen, ListGen::class.java)
            intent.putExtra("position", position)
            val args = Bundle()
            args.putSerializable("selected", selected_pages as Serializable?)
            intent.putExtra("BUNDLE", args)

            startActivity(intent)
        }
    }

}
