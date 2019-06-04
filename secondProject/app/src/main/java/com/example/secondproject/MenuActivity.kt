package com.example.secondproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)



        btnRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        btnSearch.setOnClickListener {
            startActivity<SearchActivity>()
        }
    }

}
