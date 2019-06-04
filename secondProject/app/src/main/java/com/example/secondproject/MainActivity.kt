package com.example.secondproject

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            startActivity<MenuActivity>()
        }, 2000)
    }
}