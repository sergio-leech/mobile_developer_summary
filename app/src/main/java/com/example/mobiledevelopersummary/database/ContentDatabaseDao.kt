package com.example.mobiledevelopersummary.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContentDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(myContent: MyContent)

    @Update
    suspend fun update(myContent: MyContent)

    @Delete
    suspend fun delete(myContent: MyContent)

    @Query("SELECT * from my_content_table WHERE contentId = :key")
    fun get(key: String): LiveData<MyContent>

    @Query("DELETE FROM my_content_table")
    suspend fun clear()

    @Query("SELECT * FROM my_content_table ORDER BY data DESC")
    fun getAllContents(): LiveData<List<MyContent>>
}