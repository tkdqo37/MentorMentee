package com.example.secondproject

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_mentor.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class UpdateMentorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mentor)

        btnStartUpdateMentor.setOnClickListener {

            if (etUpdateMentor.text.length == 0) {
                toast("수정할 회원코드를 입력하세요.")
            } else {
                sqlDBMentor = registerMentor.readableDatabase

                var cnt: Int = 0

                var c: Cursor
                c = sqlDBMentor.rawQuery(
                    "select * from MentorTBL where MentorCode ='" + etUpdateMentor.text.toString() + "';",
                    null
                )
                while (c.moveToNext()) {
                    cnt++
                }

                if (cnt == 0) {
                    toast("등록되지 않은 회원코드 입니다.")
                } else {
                    startActivity<UpdateMentorFinalActivity>(
                        "UpdateMentor" to etUpdateMentor.text.toString()
                    )
                    cnt = 0
                }
            }
        }

        btnUpdateMentorHome.setOnClickListener {
            startActivity<MenuActivity>()
        }
    }
}
