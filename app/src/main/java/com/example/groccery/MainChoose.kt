package com.example.groccery

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

import androidx.appcompat.app.AlertDialog;


class MainChoose : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>
    lateinit var selected_pages: ArrayList<Int>

    companion object {
        var lists = ArrayList<List<String>>()
        var titles = ArrayList<String>()
        var output = ArrayList<String>()
        var backgrounds = ArrayList<Int>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_choose)

        initLists()

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModal>()
        selected_pages = ArrayList<Int>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModal("Молочные продукты", R.drawable.milk)
        titles.add("Молочные продукты")
        courseList = courseList + GridViewModal("ВАРКА И ВЫПЕЧКА", R.drawable.bake)
        titles.add("ВАРКА И ВЫПЕЧКА")
        courseList = courseList + GridViewModal("Хлебные изделия и закуски", R.drawable.bread)
        titles.add("Хлебные изделия и закуски")
        courseList = courseList + GridViewModal("СОУСЫ И ДОБАВКИ", R.drawable.souse)
        titles.add("СОУСЫ И ДОБАВКИ")
        courseList = courseList + GridViewModal("СЛАДКОЕ И НАПИТКИ", R.drawable.sweet)
        titles.add("СЛАДКОЕ И НАПИТКИ")
        courseList = courseList + GridViewModal("ЗАМОРОЖЕННЫЕ", R.drawable.ice)
        titles.add("ЗАМОРОЖЕННЫЕ")
        courseList = courseList + GridViewModal("ОВОЩИ", R.drawable.veg)
        titles.add("ОВОЩИ")
        courseList = courseList + GridViewModal("ФРУКТЫ", R.drawable.fruit)
        titles.add("ФРУКТЫ")
        courseList = courseList + GridViewModal("УБОРКА И ОБСЛУЖИВАНИЕ ДОМА", R.drawable.clean)
        titles.add("УБОРКА И ОБСЛУЖИВАНИЕ ДОМА")


        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = GridRVAdapter(courseList = courseList, this@MainChoose)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, _ ->

            if (view.alpha != 0.5f) {
                view.alpha = 0.5f
                selected_pages.add(position)
            } else {
                view.alpha = 1.0f
                selected_pages.remove(position)
            }

            val fab: View = findViewById(R.id.fab)
            fab.setOnClickListener { view ->
                val intent = Intent(this@MainChoose, ListGen::class.java)
                intent.putExtra("position", 0)
                val args = Bundle()
                args.putSerializable("selected", selected_pages as Serializable?)
                intent.putExtra("BUNDLE", args)
                startActivity(intent)


            }
        }
    }

    fun initLists() {
        output.add("СПИСОК ПОКУПОК:")
        val page0: MutableList<String> = mutableListOf()
        page0.add("")
        page0.add("Молоко")
        page0.add("Кефир")
        page0.add("Ривион")
        page0.add("Сметана")
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
        page0.add("")
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
        page0.clear()
        page0.add("")
        page0.add("Хлеб")
        page0.add("булочки")
        page0.add("питы")
        page0.add("Жебета")
        page0.add("Бамба")
        page0.add("чипсы")
        lists.add(page0.toList())
        page0.clear()
        page0.add("")
        page0.add("Оливковое масло")
        page0.add("Спрей с маслом")
        page0.add("Уксус")
        page0.add("специи")
        page0.add("Тунa")
        page0.add("колбаса")
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
        page0.clear()
        page0.add("")
        page0.add("чай")
        page0.add("кофе")
        page0.add("Цикорий")
        page0.add("Халва")
        page0.add("печенье")
        page0.add("Конфеты")
        page0.add("ТИК-Так")
        page0.add("Ментос")
        page0.add("Мороженое")
        page0.add("Сушеные финики")
        page0.add("чернослив")
        page0.add("Крембо")
        page0.add("Зефир ")
        lists.add(page0.toList())
        page0.clear()
        page0.add("")
        page0.add("замороженное мясо")
        page0.add("замороженная рыба")
        page0.add("Бурекас")
        page0.add("замороженные фрукты")
        page0.add("тесто")
        page0.add("Вареники")
        page0.add("пельмени")
        lists.add(page0.toList())
        page0.clear()
        page0.add("")
        page0.add("Помидор")
        page0.add("огурец")
        page0.add("перец")
        page0.add("Лук")
        page0.add("фиолетовый лук")
        page0.add("Зеленый лук")
        page0.add("Сельдерей")
        page0.add("фенхель")
        page0.add("Кабачок")
        page0.add("Баклажан")
        page0.add("Листья Бэби")
        page0.add("Листья рукколы")
        page0.add("лимон")
        page0.add("мята")
        page0.add("Чеснок")
        page0.add("цветная капуста")
        page0.add("тыква")
        page0.add("Картофель")
        page0.add("батата")
        page0.add("Морковь")
        page0.add("Капуста")
        page0.add("редиска")
        page0.add("Петрушка")
        page0.add("укроп")
        page0.add("хаса")
        page0.add("Авокадо")
        page0.add("брокколи")
        lists.add(page0.toList())
        page0.clear()
        page0.add("")
        page0.add("яблоко")
        page0.add("Груша")
        page0.add("персик")
        page0.add("слива")
        page0.add("виноград")
        page0.add("Арбуз")
        page0.add("клубника")
        page0.add("Апельсин")
        page0.add("мандарин")
        page0.add("Манго")
        page0.add("ягоды")
        lists.add(page0.toList())
        page0.clear()
        page0.add("")
        page0.add("Туалетная бумага")
        page0.add("Бумажное полотенце")
        page0.add("Бумага для выпечки")
        page0.add("алюминиевая фольга")
        page0.add("Липкая бумага")
        page0.add("пакеты")
        page0.add("Гель для стирки")
        page0.add("Кондиционер для белья")
        page0.add("Средство для Дезинфекции одежды")
        page0.add("Средство для мытья полов")
        page0.add("Посудомоечная жидкость")
        page0.add("Дезинфицирующий спрей")
        page0.add("Мыло для рук")
        page0.add("Перчатки")
        page0.add("Салфетки для туалета")
        page0.add("Салфетки для уборки")
        page0.add("Спирт")
        page0.add("Спички")
        lists.add(page0.toList())

        backgrounds.add(R.drawable.back_milk)
        backgrounds.add(R.drawable.back_bake)
        backgrounds.add(R.drawable.back_bread)
        backgrounds.add(R.drawable.back_souses)
        backgrounds.add(R.drawable.back_sweets)
        backgrounds.add(R.drawable.back_ice)
        backgrounds.add(R.drawable.back_veg)
        backgrounds.add(R.drawable.back_fruit)
        backgrounds.add(R.drawable.back_clean)

    }

    override fun onBackPressed() {
        // Create the object of AlertDialog Builder class
        val builder = AlertDialog.Builder(this@MainChoose)
        // Set the message shown for the Alert
        builder.setMessage("Текущий список покупок будет удален!")
        // Set Alert Title
        builder.setTitle("Предупреждение")
        // Set Cancelable false for when the user clicks on the outside of the Dialog Box, it will remain shown
        builder.setCancelable(false)
        // Set the positive button with "Yes" text and a lambda OnClickListener
        builder.setPositiveButton("выйти") { dialog, which ->
            // When the user clicks "Yes," the app will close
            selected_pages.clear()
            output.clear()
            output.add("СПИСОК ПОКУПОК:")
            super.onBackPressed()
        }
        // Set the negative button with "No" text and a lambda OnClickListener
        builder.setNegativeButton("остаться") { dialog, which ->
            // If the user clicks "No," the dialog box is canceled
            dialog.cancel()
        }

        // Create the Alert dialog
        val alertDialog: AlertDialog = builder.create()

        // Show the Alert Dialog box
        alertDialog.show()

    }








//    override fun onBackPressed() {
//        super.onBackPressed()
//        Builde(this)
//            .setTitle("Delete entry")
//            .setMessage("Are you sure you want to delete this entry?") // Specifying a listener allows you to take an action before dismissing the dialog.
//            // The dialog is automatically dismissed when a dialog button is clicked.
//            .setPositiveButton(android.R.string.yes,
//                DialogInterface.OnClickListener { dialog, which ->
//                    selected_pages.clear()
//                    output.clear()
//                    output.add("СПИСОК ПОКУПОК:")
//                    // Continue with delete operation
//                }) // A null listener allows the button to dismiss the dialog and take no further action.
//            .setNegativeButton(android.R.string.no, null)
//            .setIcon(android.R.drawable.ic_dialog_alert)
//            .show()
//
//    }

}
