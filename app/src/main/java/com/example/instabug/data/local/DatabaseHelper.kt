package com.example.instabug.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.instabug.data.models.Word

class DatabaseHelper(context : Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        private var DATABASE_VERSION = 1
        private val DATABASE_NAME = "wordsDatabase"
        private val TABLE_WORDS = "wordsTable"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val WORDS_TABLE = ("CREATE TABLE " + TABLE_WORDS + "("
                + "word" + " TEXT,"
                + "numbers" + " TEXT" + ")")
        db?.execSQL(WORDS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + "wordsTable")
        onCreate(db)
    }

    fun readCachedData() : List<Word>{
        val wordsList:ArrayList<Word> = ArrayList<Word>()
        val selectQuery = "SELECT  * FROM $TABLE_WORDS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var word: String
        var wNumber: Int
        if (cursor.moveToFirst()) {
            do {
                word = cursor.getString(cursor.getColumnIndex("word"))
                wNumber = cursor.getInt(cursor.getColumnIndex("numbers"))
                val emp= Word(word, wNumber)
                wordsList.add(emp)
            } while (cursor.moveToNext())
        }
        return wordsList
    }

    fun cachingData(data : List<Word>): Long{
        val db = this.writableDatabase
        onUpgrade(db, DATABASE_VERSION, DATABASE_VERSION + 1)
        var success : Long = 1
        for(item in data) {
            val contentValues = ContentValues()
            contentValues.put("word", item.wordText)
            contentValues.put("numbers", item.wordNumber)
            success = db.insert(TABLE_WORDS, null, contentValues)
        }
        db.close()
        return success
    }
}