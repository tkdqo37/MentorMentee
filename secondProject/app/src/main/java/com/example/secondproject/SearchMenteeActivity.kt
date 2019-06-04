package com.example.secondproject

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search_mentee.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchMenteeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_mentee)

        registerMentee = RegisterMenteeActivity.MyDBHelperMentee(
            this,
            "MenteeDB.db",
            null,
            1
        )

        btnSearchMenteeAll.setOnClickListener {
            sqlDBMentee = registerMentee.readableDatabase
            val c: Cursor
            c = sqlDBMentee.rawQuery("select * from MenteeTBL order by MenteeCode asc;", null)

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


            tvCodeSearchMentee.setText(strCode)
            tvNameSearchMentee.setText(strName)
            tvSubjectSearchMentee.setText(strSubject)
            tvLocationSearchMentee.setText(strLocation)
            tvGenderSearchMentee.setText(strGender)
            tvMoneySearchMentee.setText(strMoney)

            c.close()
        }

        btnSearchMentee.setOnClickListener {
            sqlDBMentee = registerMentee.writableDatabase
            if (etSearchMentee.text.length == 0) {
                toast("검색할 내용을 입력하세요.")
            } else {
                sqlDBMentee = registerMentee.readableDatabase

                val c: Cursor
                c = sqlDBMentee.rawQuery(
                    "select * from MenteeTBL where MenteeSubject like '%"
                            + etSearchMentee.text.toString() + "%' order by MenteeCode asc;", null
                )

                var strCode: String = ""
                var strName: String = ""
                var strSubject: String = ""
                var strLocation: String = ""
                var strGender: String = ""
                var strMoney: String = ""

                while (c.moveToNext()) {
                    strCode += c.getString(1) + "\r\n"
                    strName += c.getString(0) + "\r\n"
                    strSubject += c.getString(2) + "\r\n"
                    strLocation += c.getString(3) + "\r\n"
                    strGender += c.getString(4) + "\r\n"
                    strMoney += c.getString(5) + "\r\n"
                }


                tvCodeSearchMentee.setText(strCode)
                tvNameSearchMentee.setText(strName)
                tvSubjectSearchMentee.setText(strSubject)
                tvLocationSearchMentee.setText(strLocation)
                tvGenderSearchMentee.setText(strGender)
                tvMoneySearchMentee.setText(strMoney)

                c.close()
            }
        }

        btnSearchMenteeHome.setOnClickListener {
            startActivity<MenuActivity>()
        }

        btnSearchMenteeDelete.setOnClickListener {
            startActivity<DeleteMenteeActivity>()
        }

        btnSearchMenteeUpdate.setOnClickListener {
            startActivity<UpdateMenteeActivity>()
        }
    }
}
