package ru.netology.nmedia.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class DbHelper(context: Context, dbVersion: Int, dbName: String, private val DDLs:Array<String>):SQLiteOpenHelper(context,dbName,null,dbVersion) {
    override fun onCreate(db: SQLiteDatabase?) {
        DDLs.forEach { query ->
            try {
                if (db != null) {
                    db.execSQL(query)
                    Log.d("DbHelper", "Executed DDL query: $query")
                }
            } catch (e: SQLException) {
                Log.e("DbHelper", "Error executing DDL query: $query", e)
            }
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}