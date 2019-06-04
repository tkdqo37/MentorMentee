package com.example.secondproject

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_delete_mentee.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DeleteMenteeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_mentee)

        btnDeleteMentee.setOnClickListener {
            if (etDeleteMentee.text.length == 0) {
                toast("삭제할 회원코드를 입력하세요.")
            } else {
                sqlDBMentee = registerMentee.readableDatabase

                var cnt: Int = 0

                var c: Cursor
                c = sqlDBMentee.rawQuery(
                    "select * from MenteeTBL where MenteeCode='" + etDeleteMentee.text.toString() + "';",
                    null
                )
                while (c.moveToNext()) {
                    cnt++
                }
                if (cnt == 0) {
                    toast("등록되지 않은 회원코드 입니다.")
                } else {
                    sqlDBMentee.execSQL(
                        "delete from MenteeTBL where MenteeCode ='" + etDeleteMentee.text.toString() + "';"
                    )
                    toast("삭제가 완료 되었습니다.")
                    startActivity<MenuActivity>()
                    cnt = 0
                }
            }
        }

        btnDeleteMenteeHome.setOnClickListener {
            startActivity<MenuActivity>()
        }
    }
}
