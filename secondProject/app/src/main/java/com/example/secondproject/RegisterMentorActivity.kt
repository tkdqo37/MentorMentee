package com.example.secondproject

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register_mentor.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

lateinit var registerMentor: RegisterMentorActivity.MyDBHelperMentor
lateinit var sqlDBMentor: SQLiteDatabase

class RegisterMentorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_mentor)

        var flag: Boolean = true

        registerMentor = MyDBHelperMentor(
            this,
            "MentorDB.db",
            null,
            1
        )

        btnInsertMentor.setOnClickListener {
            sqlDBMentor = registerMentor.writableDatabase

            if (etNameMentor.text.length == 0 || etCodeMentor.text.length == 0 || etSubjectMentor.text.length == 0 || etLocationMentor.text.length == 0 || etGenderMentor.text.length == 0 || etMoneyMentor.text.length == 0) {
                toast("입력하지 않은 값이 있습니다.").show()

            } else {
                sqlDBMentor = registerMentor.readableDatabase

                var c: Cursor
                c = sqlDBMentor.rawQuery("select Mentorcode from MentorTBL", null)

                while (c.moveToNext()) {
                    if (c.getString(0) == etCodeMentor.text.toString()) {
                        flag = false
                        toast("이미 등록된 회원코드 입니다.").show()
                    }
                }

                if (flag) {
                    sqlDBMentor.execSQL(
                        "insert into MentorTBL values("
                                + etCodeMentor.text.toString() + ",'"
                                + etNameMentor.text.toString() + "','"
                                + etSubjectMentor.text.toString() + "','"
                                + etLocationMentor.text.toString() + "','"
                                + etGenderMentor.text.toString() + "',"
                                + etMoneyMentor.text.toString() + ");"
                    )
                    toast("입력이 완료 되었습니다.").show()
                    startActivity<MenuActivity>()
                }

                flag = true
            }
        }
    }

    class MyDBHelperMentor(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) :
        SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table MentorTBL(MentorCode integer, MentorName char(20), MentorSubject char(20), MentorLocation char (20), MentorGender char(20), MentorMoney integer);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
}
