package com.neeraj.wordapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    public abstract fun getWordDao(): WordDao


    companion object {

        @Volatile
        private var mInstance: WordRoomDatabase? = null

        fun getDatabaseInstance(context: Context, scope: CoroutineScope): WordRoomDatabase {

            return mInstance ?: synchronized(this) {

                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                mInstance = instace

                // return instance
                instace
            }
        }
    }


    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            mInstance?.let { database ->

                scope.launch {
                    populateDatabase(database.getWordDao())
                }
            }
        }


        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete All content here
            wordDao.delete()

            var word = Word("Hello")
            wordDao.insert(word)

            word = Word("World!")
            wordDao.insert(word)

        }
    }
}