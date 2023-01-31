package com.neeraj.wordapp

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository (private val wordDao: WordDao) {

    val allWords : Flow<List<Word>> = wordDao.getAllWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWord(word: Word) {
        wordDao.insert(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteWord() {
        wordDao.delete()
    }

}