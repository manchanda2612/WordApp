package com.neeraj.wordapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())


    val database : WordRoomDatabase by lazy {
        WordRoomDatabase.getDatabaseInstance(this, applicationScope)
    }


    val repository : WordRepository by lazy {
        WordRepository(database.getWordDao())
    }

}