package com.neeraj.wordapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (word : Word)

    @Query("DELETE FROM word_table")
    suspend fun delete ()

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords() : Flow<List<Word>>

}