package com.jmenmar.ikasi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmenmar.ikasi.data.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

    // SELECT
    @Query("SELECT * FROM word")
    fun getAllWords(): Flow<List<WordEntity>>

    @Query("SELECT * FROM word WHERE title LIKE :filter")
    fun getWordsBy(filter: Int): Flow<List<WordEntity>>

    @Query("SELECT * FROM word ORDER BY RANDOM() LIMIT :length")
    suspend fun getRandomWords(length: Int): List<WordEntity>

    // UPDATE
    @Query("UPDATE word SET meaning = :meaning WHERE title LIKE :title")
    suspend fun updateWord(title: String, meaning: String)

    // DELETE
    @Query("DELETE FROM word WHERE title LIKE :title")
    suspend fun deleteWord(title: String)
}