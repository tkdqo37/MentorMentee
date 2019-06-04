package com.example.secondproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnSearchMentor.setOnClickListener {
            startActivity<SearchMentorActivity>()
        }

        btnSearchMentee.setOnClickListener {
            startActivity<SearchMenteeActivity>()
        }
    }
}
