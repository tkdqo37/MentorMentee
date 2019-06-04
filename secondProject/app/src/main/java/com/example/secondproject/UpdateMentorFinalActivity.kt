package com.example.secondproject

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_mentor_final.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class UpdateMentorFinalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mentor_final)

        registerMentor = RegisterMentorActivity.MyDBHelperMentor(
            this,
            "MentorDB.db",
            null,
            1
        )

        sqlDBMentor = registerMentor.readableDatabase
        val UpdateMentor = intent.getStringExtra("UpdateMentor").toString()

        tvUpdateMentorCode.setText("회원코드 : " + UpdateMentor)

        var c: Cursor
        c = sqlDBMentor.rawQuery("select * from MentorTBL where MentorCode = '" + UpdateMentor + "';", null)

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

        etUpdateNameMentor.setText(strName)
        etUpdateSubjectMentor.setText(strSubject)
        etUpdateLocationMentor.setText(strLocation)
        etUpdateGenderMentor.setText(strGender)
        etUpdateMoneyMentor.setText(strMoney)

        c.close()

        btnUpdateCancelMentor.setOnClickListener {
            startActivity<MenuActivity>()
        }

        btnUpdateInsertMentor.setOnClickListener {

            if (etUpdateNameMentor.text.length == 0 || etUpdateSubjectMentor.text.length == 0 || etUpdateLocationMentor.text.length == 0 || etUpdateGenderMentor.text.length == 0 || etUpdateMoneyMentor.text.length == 0) {
                toast("입력하지 않은 값이 있습니다.").show()

            } else {
                sqlDBMentor = registerMentor.writableDatabase

                var cu: Cursor
                cu = sqlDBMentor.rawQuery("select * from MentorTBL", null)

                sqlDBMentor.execSQL(
                    "delete from MentorTBL where MentorCode ='" + UpdateMentor + "';"
                )

                sqlDBMentor.execSQL(
                    "insert into MentorTBL values("
                            + UpdateMentor + ",'"
                            + etUpdateNameMentor.text.toString() + "','"
                            + etUpdateSubjectMentor.text.toString() + "','"
                            + etUpdateLocationMentor.text.toString() + "','"
                            + etUpdateGenderMentor.text.toString() + "',"
                            + etUpdateMoneyMentor.text.toString() + ");"
                )
                toast("수정이 완료 되었습니다.").show()
                startActivity<MenuActivity>()
            }
        }

        btnUpdateDeleteMentor.setOnClickListener {
            etUpdateNameMentor.setText("")
            etUpdateSubjectMentor.setText("")
            etUpdateLocationMentor.setText("")
            etUpdateGenderMentor.setText("")
            etUpdateMoneyMentor.setText("")
        }
    }

}
