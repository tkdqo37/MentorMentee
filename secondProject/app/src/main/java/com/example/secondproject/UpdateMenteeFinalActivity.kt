package com.example.secondproject

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_mentee_final.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class UpdateMenteeFinalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mentee_final)

        registerMentee = RegisterMenteeActivity.MyDBHelperMentee(
            this,
            "MenteeDB.db",
            null,
            1
        )

        sqlDBMentee = registerMentee.readableDatabase
        val UpdateMentee = intent.getStringExtra("UpdateMentee").toString()

        tvUpdateMenteeCode.setText("회원코드 : " + UpdateMentee)

        var c: Cursor
        c = sqlDBMentee.rawQuery("select * from MenteeTBL where MenteeCode = '" + UpdateMentee + "';", null)

        var strName: String = ""
        var strSubject: String = ""
        var strLocation: String = ""
        var strGender: String = ""
        var strMoney: String = ""

        while (c.moveToNext()) {
            strName += c.getString(1)
            strSubject += c.getString(2)
            strLocation += c.getString(3)
            strGender += c.getString(4)
            strMoney += c.getString(5)
        }

        etUpdateNameMentee.setText(strName)
        etUpdateSubjectMentee.setText(strSubject)
        etUpdateLocationMentee.setText(strLocation)
        etUpdateGenderMentee.setText(strGender)
        etUpdateMoneyMentee.setText(strMoney)

        c.close()

        btnUpdateCancelMentee.setOnClickListener {
            startActivity<MenuActivity>()
        }

        btnUpdateInsertMentee.setOnClickListener {

            if (etUpdateNameMentee.text.length == 0 || etUpdateSubjectMentee.text.length == 0 || etUpdateLocationMentee.text.length == 0 || etUpdateGenderMentee.text.length == 0 || etUpdateMoneyMentee.text.length == 0) {
                toast("입력하지 않은 값이 있습니다.").show()

            } else {
                sqlDBMentee = registerMentee.writableDatabase

                var cu: Cursor
                cu = sqlDBMentee.rawQuery("select * from MenteeTBL", null)

                sqlDBMentee.execSQL(
                    "delete from MenteeTBL where MenteeCode ='" + UpdateMentee + "';"
                )

                sqlDBMentee.execSQL(
                    "insert into MenteeTBL values("
                            + UpdateMentee + ",'"
                            + etUpdateNameMentee.text.toString() + "','"
                            + etUpdateSubjectMentee.text.toString() + "','"
                            + etUpdateLocationMentee.text.toString() + "','"
                            + etUpdateGenderMentee.text.toString() + "',"
                            + etUpdateMoneyMentee.text.toString() + ");"
                )
                toast("수정이 완료 되었습니다.").show()
                startActivity<MenuActivity>()
            }
        }

        btnUpdateDeleteMentee.setOnClickListener {
            etUpdateNameMentee.setText("")
            etUpdateSubjectMentee.setText("")
            etUpdateLocationMentee.setText("")
            etUpdateGenderMentee.setText("")
            etUpdateMoneyMentee.setText("")
        }
    }
}
