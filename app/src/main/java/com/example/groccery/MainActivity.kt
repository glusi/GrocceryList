package com.example.groccery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    lateinit var selected_pages: ArrayList<Int>

    companion object {
        var lists = ArrayList<List<String>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLists()

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModal>()
        selected_pages = ArrayList<Int>()

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
            if(view.alpha!=0.5f){
                view.alpha=0.5f
                selected_pages.add(position)}
            else{
            view.alpha = 1.0f
            selected_pages.remove(position)
            }

            val fab: View = findViewById(R.id.fab)
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Here's a Snackbar "+selected_pages, Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
                val intent = Intent(this@MainActivity, ListGen::class.java)
                intent.putExtra("position",selected_pages[0])
                val args = Bundle()
                args.putSerializable("selected", selected_pages as Serializable?)
                intent.putExtra("BUNDLE", args)
                startActivity(intent)


            }
        }
    }

    fun initLists(){
        val page0 : MutableList<String> = mutableListOf()
        page0.add("молоко")
        page0.add("Кефир")
        page0.add("ривион")
        page0.add("сметана")
        page0.add("йогурт")
        page0.add("сливочное масло")
        page0.add("Желтый сыр")
        page0.add("Cыр эмек")
        page0.add("котэдж")
        page0.add("творог")
        page0.add("Цфатский сыр ")
        page0.add("намазки для хлеба")
        page0.add("Сыр фета")
        page0.add("Сыр Дзадзики")
        page0.add("лàбанэ")
        page0.add("рикотта")
        lists.add(page0.toList())
        page0.clear()
        page0.add("макаронные изделия")
        page0.add("Гречка")
        page0.add("кускус")
        page0.add("Я́чневая крупа́")
        page0.add("Рис")
        page0.add("Овсяная каша")
        page0.add("манная крупа")
        page0.add("Панировочные сухари")
        page0.add("мука")
        page0.add("Соль")
        page0.add("яйца ")
        page0.add("Порошок для выпечки")
        page0.add("Питьевая сода")
        page0.add("Сахар")
        page0.add("Стевия")
        page0.add("коричневый сахар")
        page0.add("какао")
        page0.add("Лимонная соль")
        page0.add("сливки")
        page0.add("Ванильный сахар")
        page0.add("Экстракт ванили ")
        lists.add(page0.toList())
//        val page2 = ArrayList<String>()
        page0.clear()
        page0.add("Оливковое масло")
        page0.add("Спрей с маслом")
        page0.add("Уксус")
        page0.add("специи")
        page0.add("Тунa")
        page0.add("оливки")
        page0.add("томатный соус")
        page0.add("Хумус")
        page0.add("салат из баклажанов")
        page0.add("Кетчуп")
        page0.add("майонез")
        page0.add("Матбуха")
        page0.add("терияки")
        page0.add("Соевый соус")
        page0.add("чили")
        page0.add("мед")
        page0.add("кленовый сироп")
        page0.add("силан")
        page0.add("Нуте́лла")
        page0.add("Сгущённое молоко")
        page0.add("Сгущённое молоко")
        lists.add(page0.toList())
    }
}
