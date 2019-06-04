package com.example.secondproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegisterMentor.setOnClickListener {
            startActivity<RegisterMentorActivity>()
        }

        btnRegisterMentee.setOnClickListener {
            startActivity<RegisterMenteeActivity>()
        }

    }
}
