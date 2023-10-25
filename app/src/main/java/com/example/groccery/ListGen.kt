package com.example.groccery

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.util.LruCache
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.Serializable


class ListGen : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var selected_lines: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_gen)
    }

    override fun onResume() {
        val fab: View = findViewById(R.id.next)
        super.onResume()
        selected_lines = ArrayList<String>()

        var bundle: Bundle? = intent.extras
        val args = intent.getBundleExtra("BUNDLE")
        val selectedPages = args!!.getSerializable("selected") as ArrayList<Int>
        var position = bundle!!.getInt("position")

        var titles: ArrayList<String> = MainChoose.titles
        val title: TextView = findViewById(R.id.title)

        var lists: ArrayList<List<String>> = MainChoose.lists
        listView = findViewById(R.id.list_products)

        var backgrounds: ArrayList<Int> = MainChoose.backgrounds

        var output: ArrayList<String> = MainChoose.output



        if (position == selectedPages.size) {
            title.text = "Список покупок готов!"
            if (output != null && output.count() > 1)
                output = output.distinct() as ArrayList<String>
            listView.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, output)
            listView.setBackgroundResource(0)
            val fab: View = findViewById(R.id.next)
            fab.setOnClickListener { view ->

                val listView = findViewById<ListView>(R.id.list_products) // Replace with your ListView's ID

                listView.post {

                    val bitmap : Bitmap? = getBitmapFromView(findViewById(R.id.list_products))
                    // Save the bitmap as an image file
                    val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "listview_image.png")
                    val outputStream = FileOutputStream(file)
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    }
                    outputStream.close()

                    // Share the saved image using FileProvider
                    val bmpUri = FileProvider.getUriForFile(this, "com.codepath.fileprovider", file)
                    if (bitmap != null) {
                        saveImageToGallery(bitmap)
                        val toast = Toast.makeText(
                            applicationContext, " Изображение сохранено в галерее",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }

                    val intent = Intent(Intent.ACTION_SEND)
                    //intent.setType("image/*")
                    intent.putExtra(Intent.EXTRA_STREAM, bmpUri)
                    intent.type = "image/*"
                    startActivity(Intent.createChooser(intent, "send"))
                }

            }
        }else{
            val pageNumber = selectedPages.get(position)
            title.text = titles[pageNumber]

            val resource: BitmapDrawable =
                getResources().getDrawable((backgrounds.get(pageNumber))) as BitmapDrawable
            resource.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            listView.setBackground(resource)

            val background = listView.background
            background.alpha = 35

            listView.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, lists[pageNumber])

            listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            listView.onItemClickListener =
                AdapterView.OnItemClickListener { _, view_item, position_iternal, _ ->
//                    Toast.makeText(
//                        applicationContext, " selected",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    if (view_item.alpha != 0.5f) {
                        val border = GradientDrawable()
                        border.setColor(Color.argb(220, 50, 168, 78)) //white background
                        view_item.setBackground(border)
//                        }
                        view_item.alpha = 0.5f
                        selected_lines.add(listView.getItemAtPosition(position_iternal).toString())

                    } else {
                        view_item.alpha = 1.0f
                        view_item.setBackground(null)
                        selected_lines.remove(
                            listView.getItemAtPosition(position_iternal).toString()
                        )
                    }
                }

            position += 1
            val fab: View = findViewById(R.id.next)
            fab.setOnClickListener { view ->
                if(selected_lines!=null && selected_lines.isNotEmpty())
                    selected_lines = ArrayList(selected_lines.toSet())
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


    fun getBitmapFromView(listView: ListView): Bitmap? {
        val adapter: ListAdapter = listView.adapter
        var bigBitmap: Bitmap? = null
        if (adapter != null) {
            val size = adapter.count
            var height = 0
            val paint = Paint()
            var iHeight = 0
            val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

            // Use 1/8th of the available memory for this memory cache.
            val cacheSize = maxMemory / 8
            val bitmapCache = LruCache<String, Bitmap>(cacheSize)

            for (i in 0 until size) {
                val itemView = adapter.getView(i, null, listView) as View
                itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                )
                itemView.layout(0, 0, itemView.measuredWidth, itemView.measuredHeight)
                itemView.isDrawingCacheEnabled = true
                itemView.buildDrawingCache()
                val drawingCache = itemView.drawingCache

                if (drawingCache != null) {
                    bitmapCache.put(i.toString(), drawingCache)
                }
                height += itemView.measuredHeight
            }

            bigBitmap = Bitmap.createBitmap(listView.width, height, Bitmap.Config.ARGB_8888)
            val bigCanvas = Canvas(bigBitmap)
            bigCanvas.drawColor(Color.WHITE)

            for (i in 0 until size) {
                val bitmap = bitmapCache.get(i.toString())
                bigCanvas.drawBitmap(bitmap, 0f, iHeight.toFloat(), paint)
                iHeight += bitmap?.height ?: 0
                bitmap?.recycle()
            }
        }
        return bigBitmap
    }
    private fun saveImageToGallery(bitmap: Bitmap) {
        val filename = "grocery-list-"+System.currentTimeMillis()+".png"
        val savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

        val file = File(savePath, filename)
        val fileOutputStream = FileOutputStream(file)

        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Notify the MediaScanner to update the gallery
        MediaScannerConnection.scanFile(
            this, arrayOf(file.toString()), null
        ) { _, _ -> }
    }

}
