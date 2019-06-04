package com.example.secondproject

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register_mentee.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

lateinit var registerMentee: RegisterMenteeActivity.MyDBHelperMentee
lateinit var sqlDBMentee: SQLiteDatabase

class RegisterMenteeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_mentee)

        var flag: Boolean = true

        registerMentee = MyDBHelperMentee(
            this,
            "MenteeDB.db",
            null,
            1
        )

        btnInsertMentee.setOnClickListener {
            sqlDBMentee = registerMentee.writableDatabase
            if (etNameMentee.text.length == 0 || etCodeMentee.text.length == 0 || etSubjectMentee.text.length == 0 || etLocationMentee.text.length == 0 || etGenderMentee.text.length == 0 || etMoneyMentee.text.length == 0) {
                toast("입력하지 않은 값이 있습니다.").show()
            } else {
                sqlDBMentee = registerMentee.readableDatabase

                val c: Cursor
                c = sqlDBMentee.rawQuery("select Menteecode from MenteeTBL", null)

                while (c.moveToNext()) {
                    if (c.getString(0) == etCodeMentee.text.toString()) {
                        flag = false
                        toast("이미 등록된 회원코드 입니다.").show()
                    }
                }

                if (flag) {
                    sqlDBMentee.execSQL(
                        "insert into MenteeTBL values("
                                + etCodeMentee.text.toString() + ",'"
                                + etNameMentee.text.toString() + "','"
                                + etSubjectMentee.text.toString() + "','"
                                + etLocationMentee.text.toString() + "','"
                                + etGenderMentee.text.toString() + "',"
                                + etMoneyMentee.text.toString() + ");"
                    )
                    toast("입력이 완료 되었습니다.")
                    startActivity<MenuActivity>()
                }

                flag = true
            }
        }
    }

    class MyDBHelperMentee(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) :
        SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table MenteeTBL(MenteeCode integer, MenteeName char(20), MenteeSubject char(20), MenteeLocation char (20), MenteeGender char(20), MenteeMoney integer);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
}
