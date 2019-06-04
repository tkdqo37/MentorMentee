package com.example.secondproject

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search_mentor.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchMentorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_mentor)

        registerMentor = RegisterMentorActivity.MyDBHelperMentor(
            this,
            "MentorDB.db",
            null,
            1
        )

        btnSearchMentorAll.setOnClickListener {
            sqlDBMentor = registerMentor.readableDatabase
            val c: Cursor
            c = sqlDBMentor.rawQuery("select * from MentorTBL order by MentorCode asc;", null)

            var strCode: String = ""
            var strName: String = ""
            var strSubject: String = ""
            var strLocation: String = ""
            var strGender: String = ""
            var strMoney: String = ""

            while (c.moveToNext()) {
                strCode += c.getString(0) + "\n"
                strName += c.getString(1) + "\n"
                strSubject += c.getString(2) + "\n"
                strLocation += c.getString(3) + "\n"
                strGender += c.getString(4) + "\n"
                strMoney += c.getString(5) + "\n"
            }

            tvCodeSearchMentor.setText(strCode)
            tvNameSearchMentor.setText(strName)
            tvSubjectSearchMentor.setText(strSubject)
            tvLocationSearchMentor.setText(strLocation)
            tvGenderSearchMentor.setText(strGender)
            tvMoneySearchMentor.setText(strMoney)

            c.close()
        }

        btnSearchMentor.setOnClickListener {
            sqlDBMentor = registerMentor.writableDatabase
            if (etSearchMentor.text.length == 0) {
                toast("검색할 내용을 입력하세요.").show()
            } else {
                sqlDBMentor = registerMentor.readableDatabase

                val c: Cursor
                c = sqlDBMentor.rawQuery(
                    "select * from MentorTBL where MentorSubject like '%"
                            + etSearchMentor.text.toString() + "%'order by MentorCode asc;", null
                )

                var strCode: String = ""
                var strName: String = ""
                var strSubject: String = ""
                var strLocation: String = ""
                var strGender: String = ""
                var strMoney: String = ""

                while (c.moveToNext()) {
                    strCode += c.getString(0) + "\n"
                    strName += c.getString(1) + "\n"
                    strSubject += c.getString(2) + "\n"
                    strLocation += c.getString(3) + "\n"
                    strGender += c.getString(4) + "\n"
                    strMoney += c.getString(5) + "\n"
                }


                tvCodeSearchMentor.setText(strCode)
                tvNameSearchMentor.setText(strName)
                tvSubjectSearchMentor.setText(strSubject)
                tvLocationSearchMentor.setText(strLocation)
                tvGenderSearchMentor.setText(strGender)
                tvMoneySearchMentor.setText(strMoney)

                c.close()


            }
        }

        btnSearchMentorHome.setOnClickListener {
            startActivity<MenuActivity>()
        }

        btnSearchMentorDelete.setOnClickListener {
            startActivity<DeleteMentorActivity>()
        }

        btnSearchMentorUpdate.setOnClickListener {
            startActivity<UpdateMentorActivity>()
        }

    }
}
