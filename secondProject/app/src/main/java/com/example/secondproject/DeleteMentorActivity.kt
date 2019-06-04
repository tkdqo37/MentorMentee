package com.example.secondproject

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_delete_mentor.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DeleteMentorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_mentor)

        btnDeleteMentor.setOnClickListener {
            if (etDeleteMentor.text.length == 0) {
                toast("삭제할 회원코드를 입력하세요.")
            } else {
                sqlDBMentor = registerMentor.readableDatabase

                var cnt: Int = 0

                var c: Cursor
                c = sqlDBMentor.rawQuery(
                    "select * from MentorTBL where MentorCode='" + etDeleteMentor.text.toString() + "';",
                    null
                )
                while (c.moveToNext()) {
                    cnt++
                }
                if (cnt == 0) {
                    toast("등록되지 않은 회원코드 입니다.")
                } else {
                    sqlDBMentor.execSQL(
                        "delete from MentorTBL where MentorCode ='" + etDeleteMentor.text.toString() + "';"
                    )
                    toast("삭제가 완료 되었습니다.")
                    startActivity<MenuActivity>()
                    cnt = 0
                }
            }
        }

        btnDeleteMentorHome.setOnClickListener {
            startActivity<MenuActivity>()
        }
    }
}
