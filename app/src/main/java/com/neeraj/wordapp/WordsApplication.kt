package com.neeraj.wordapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database : WordRoomDatabase by lazy {
        WordRoomDatabase.getDatabaseInstance(this, applicationScope)
    }

    val repository : WordRepository by lazy {
        WordRepository(database.getWordDao())
    }
}