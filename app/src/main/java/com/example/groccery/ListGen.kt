package com.example.groccery
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class ListGen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_gen)

        var bundle :Bundle ?=intent.extras
//        var selected_pages = bundle!!.getA("selected")
        var position = bundle!!.getInt("position")
        print(position)
        position+=1
        val fab: View = findViewById(R.id.next)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar "+position, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            val intent = Intent(this@ListGen, ListGen::class.java)
            intent.putExtra("position", 0)
//                val args = Bundle()
//                args.putSerializable("ARRAYLIST", selected_pages as Serializable?)
//                intent.putExtra("selected", args)
            startActivity(intent)
        }
    }
}
