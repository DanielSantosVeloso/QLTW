package com.example.qltw

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.selects.select

class sqlHelper(private val context: Context):
  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "BancoUsuario.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "dados"

        //usuarios
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_EMAIL = "email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
              "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
              "$COLUMN_USERNAME TEXT," +
              "$COLUMN_PASSWORD TEXT," +
              "$COLUMN_EMAIL TEXT)")

        db?.execSQL(createTableQuery)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun inserirUsuario(username:String, password:String, email:String): Long
    {
        val values = ContentValues().apply {
             put(COLUMN_USERNAME, username)
             put(COLUMN_PASSWORD, password)
            put(COLUMN_EMAIL, email)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)

    }
    fun lerUsuario(password: String, email: String): Boolean
    {
        val db = readableDatabase
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(email,password)
        val cursor = db.query(TABLE_NAME,null, selection,selectionArgs, null, null, null)

        val usuarioExiste = cursor.count > 0
        cursor.close()
        return usuarioExiste
    }
    fun existeUsuario(email: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val usuarioExiste = cursor.count > 0
        cursor.close()
        return usuarioExiste
    }
  }