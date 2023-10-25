package com.example.groccery

import android.content.Intent
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val back_main : View = findViewById(R.id.back_main)
        val resource: BitmapDrawable =
            getResources().getDrawable(R.drawable.back_main) as BitmapDrawable
        resource.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        back_main.setBackground(resource)
        val background = back_main.background
        background.alpha = 50

        val button: Button = findViewById(R.id.button)
        button.getBackground().setAlpha(90)
        button.setOnClickListener {
            val intent = Intent(this, MainChoose::class.java)
            startActivity(intent)
        }
    }
}